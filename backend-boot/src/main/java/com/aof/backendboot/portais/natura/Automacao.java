package com.aof.backendboot.portais.natura;

import com.aof.backendboot.DTO.CategoryDTO;
import com.aof.backendboot.DTO.ProductDTO;
import com.aof.backendboot.utils.Regex;
import com.aof.backendboot.utils.Utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Automacao {
    public static List<ProductDTO> loadProdutos(Elements elementos, Integer tipo) {

        List<ProductDTO> list = new ArrayList<>();
        List<CategoryDTO> categoryDTO = new ArrayList<>();
        String nome;
        String classificacao;
        String descricao;
        String valor;
        String imgUrl;

        try {

            for (Element e : elementos) {
                nome = e.getElementsByTag("h6").text();
                classificacao = Regex.calcular(">(\\d.\\.?\\d?)", e.toString()).get(0);
                descricao = e.getElementsByTag("h5").text();
                valor = e.getElementsByTag("p").text() == "" ? Regex.calcular(";(\\d{2}.?,?\\d{2})", e.toString()).get(0) : e.getElementsByTag("p").text();
                imgUrl = e.select("img").attr("src");

                ProductDTO productDTO = new ProductDTO();
                productDTO.setId(null);
                productDTO.setDate(Instant.now());
                productDTO.setName(nome);
                productDTO.setClassification(classificacao);
                productDTO.setDescription(descricao);
                productDTO.setPrice(valor);
                productDTO.setImgUrl(imgUrl);
                categoryDTO.add(new CategoryDTO(Long.parseLong(tipo.toString()), nome));
                productDTO.setCategories(categoryDTO);
                if (!Utils.compareIsEquals(productDTO, list)) {
                    list.add(productDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<ProductDTO> automacaoProdutos(Integer tipo) {

        try {

            System.setProperty("webdriver.chrome.driver", "src/test/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
//            options.addArguments("headless");
//            options.setHeadless(true);


            options.addArguments("--remote-allow-origins=*");
            options.addArguments("start-maximized");

//            Logger.getLogger("org.asynchttpclient.netty.channel").setLevel(Level.OFF);
            ChromeDriver driver = new ChromeDriver(options);
            String baseUrl = "https://www.natura.com.br/c/tudo-em-";
            String urlSufixo = "&pageSize=48&sort=az";

            try {

                Elements pages = new Elements();
                int i = 0;
                boolean parada = false;
                Elements divs;
                while (!parada) {

                    Thread.sleep(2000);
                    String url = "";

                    if (tipo == 0) {
                        url = baseUrl + "perfumaria?page=" + i + urlSufixo;
                    } else if (tipo == 1) {
                        url = baseUrl + "corpo-e-banho?page=" + i + urlSufixo;
                    } else if (tipo == 2) {
                        url = baseUrl + "cabelos?page=" + i + urlSufixo;
                    } else if (tipo == 3) {
                        url = baseUrl + "rosto?page=" + i + urlSufixo;
                    } else if (tipo == 4) {
                        url = baseUrl + "maquiagem?page=" + i + urlSufixo;
                    } else if (tipo == 5) {
                        url = baseUrl + "infantil?page=" + i + urlSufixo;
                    }

                    driver.get(url);
                    Thread.sleep(2000);
                    i++;
                    Document doc = Jsoup.parse(driver.getPageSource());

                    if (tipo == 0) {
                        divs = doc.selectXpath("//*[@id=\"root\"]/div[1]/div[1]/div[4]/div/div/div[3]");
                    } else {
                        divs = doc.selectXpath("//*[@id=\"root\"]/div[1]/div[1]/div[3]/div/div/div[3]");
                    }


                    Document html = Jsoup.parse(driver.getPageSource());

                    if (tipo == 0) {
                        if (html.selectXpath("//*[@id=\"root\"]/div[1]/div[1]/div[4]/div/div/div[3]/div[1]/div/div[1]").size() == 0) {
                            parada = true;
                        }
                    } else {
                        if (html.selectXpath("//*[@id=\"root\"]/div[1]/div[1]/div[3]/div/div/div[3]/div[1]/div/div[1]").size() == 0) {
                            parada = true;
                        }
                    }
                    for (Element div : divs) {
                        if (tipo == 0) {
                            pages.addAll(div.selectXpath("//*[@id=\"root\"]/div[1]/div[1]/div[4]/div/div/div[3]/div[1]/div").first().getElementsByClass("MuiGrid-root MuiGrid-item MuiGrid-grid-xs-4"));
                        } else {
                            pages.addAll(div.selectXpath("//*[@id=\"root\"]/div[1]/div[1]/div[3]/div/div/div[3]").first().getElementsByClass("MuiGrid-root MuiGrid-item MuiGrid-grid-xs-4"));
                        }

                    }

                }

                Thread.sleep(2000);


                return loadProdutos(pages, tipo);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                driver.quit();
            }

        } catch (Exception e) {

        }
        return null;
    }

}
