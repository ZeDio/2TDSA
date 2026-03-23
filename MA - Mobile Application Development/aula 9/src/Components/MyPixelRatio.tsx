import {
  PixelRatio,
  Image,
  ScrollView,
  Text,
  View,
  StyleSheet,
} from "react-native";

const size = 50;

const cat = {
  uri: "https://reactnative.dev/docs/assets/p_cat1.png",
  width: size,
  height: size,
};

const MyPixelRatio = () => {
  return (
    <ScrollView>
      <View style={styles.container}>
        <Text>Current pixel ratio is:</Text>
        <Text>{PixelRatio.get()}</Text>
      </View>
      <View style={styles.container}>
        <Text>Current font scale is:</Text>
        <Text>{PixelRatio.getFontScale()}</Text>
      </View>
      <View style={styles.container}>
        <Text>On this device images with a layout width of</Text>
        <Text>{size} px</Text>
        <Image source={cat} />
      </View>
      <View style={styles.container}>
        <Text>requires images with a pixel width of</Text>
        <Text>{PixelRatio.getPixelSizeForLayoutSize(size)}</Text>
        <Image
          source={cat}
          style={{
            width: PixelRatio.getPixelSizeForLayoutSize(size),
            height: PixelRatio.getPixelSizeForLayoutSize(size),
          }}
        />
      </View>
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    justifyContent: "center",
    alignItems: "center",
  },
});

export default MyPixelRatio;
