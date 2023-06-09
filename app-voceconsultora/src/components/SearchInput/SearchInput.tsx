import React from "react";
import { TextInput, View } from "react-native";
// import { theme } from "../styles";
interface SearchProps {
  placeholder: string;
  search: string;
  setSearch: Function;
}

const SearchInput: React.FC<SearchProps> = ({
  placeholder,
  search,
  setSearch,
 
}) => {
  return (
    <View >
      <TextInput
        // style={theme.searchInput}
        placeholder={placeholder}
        value={search}
        onChangeText={(text) => setSearch(text)}
      />
    </View>
  );
};
export default SearchInput;