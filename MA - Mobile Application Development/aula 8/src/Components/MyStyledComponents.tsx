import { StyleSheet, Text, View } from "react-native";

const MyStyledComponent = () => {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>React Native</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 24,
    backgroundColor: "#0000ff",
  },
  title: {
    textAlign: "center",
    padding: 8,
    borderWidth: 4,
    borderColor: "#20232a",
    borderRadius: 6,
    backgroundColor: "#61dafb",
    color: "#20232a",
    fontSize: 30,
    fontWeight: "bold",
  },
});

export default MyStyledComponent;
