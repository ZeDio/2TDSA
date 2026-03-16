import React from "react";

import { View, Image } from "react-native";

const MyImage = () => {
  return (
    <View style={{ paddingTop: 50 }}>
      <Image
        style={{ width: 50, height: 50 }}
        source={{ uri: "https://reactnative.dev/img/tiny_logo.png" }}
      />
      <Image
        style={{ width: 66, height: 58 }}
        source={{ uri: "https://reactnative.dev/img/tiny_logo.png" }}
      />
    </View>
  );
};

export default MyImage;