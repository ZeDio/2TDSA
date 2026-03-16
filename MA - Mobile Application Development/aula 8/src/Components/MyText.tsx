import React, { useState } from "react";

import { Text } from "react-native";

const MyText = () => {
  const [titleText, setTitleText] = useState(`Bird's Nest`);
  const bodyText = `This is not really a bird nest`;

  const onPressTitle = () => {
    setTitleText(`Bird's Nest [pressed]`);
  };

  return (
    <Text style={{ fontFamily: "Cochin" }}>
      <Text style={{ fontSize: 20, fontWeight: "bold" }} onPress={onPressTitle}>
        {titleText}
        {"\n"}
        {"\n"}
      </Text>
      <Text numberOfLines={5}>{bodyText}</Text>
      <Text>To colocando mais texto aqui</Text>
      <Text>To colocando mais texto aqui</Text>
      <Text>To colocando mais texto aqui</Text>
      <Text>To colocando mais texto aqui{"\n"}</Text>
      <Text style={{ fontSize: 72, fontWeight: "bold" }}>
        To colocando mais texto aqui
      </Text>
      <Text>To colocando mais texto aqui{"\n"}</Text>
    </Text>
  );
};

export default MyText;
