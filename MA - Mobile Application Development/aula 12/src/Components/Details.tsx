import { View, Text } from "react-native";

const Details = ({ route }) => {
  const { task } = route.params;

  return (
    <View>
      <Text>Detalhes da tarefa:</Text>
      <Text>{task}</Text>
    </View>
  );
};

export default Details;