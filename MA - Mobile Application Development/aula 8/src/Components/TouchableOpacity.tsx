import { TouchableOpacity, Text } from "react-native";

const MyTouchableOpacity = () => {
  const onPress = () => console.log("Pressed in");

  return (
    <TouchableOpacity onPress={onPress}>
      <Text>I'm clickable</Text>
      <Text>I'm clickable</Text>
      <Text>I'm clickable</Text>
      <Text>I'm clickable</Text>
      <Text>I'm clickable</Text>
      <Text>I'm clickable</Text>
    </TouchableOpacity>
  );
};

export default MyTouchableOpacity;
