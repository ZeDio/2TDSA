import { ActivityIndicator, View, StyleSheet } from "react-native";

const MyActivityIndicator = () => {
  return (
    <View style={styles.container}>
      <ActivityIndicator size="large" />
      <ActivityIndicator size="small" color="#0000ff" />
      <ActivityIndicator size="large" color="#r" />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    flexDirection: "row",
    padding: 10,
  },
});

export default MyActivityIndicator;
