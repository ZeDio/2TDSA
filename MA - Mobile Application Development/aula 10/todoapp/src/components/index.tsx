import { useState } from "react";
import { View, FlatList, Button, Text, TextInput } from "react-native";

const Tasks = () => {
  const [task, setTask] = useState<string>("");
  const [tasks, setTasks] = useState<string[]>([]);

  const add = () => {
    setTasks((previous) => [...previous, task]); // setTasks(([...tasks, task]);
    setTask("");
  };

  return (
    <View>
      <TextInput onChangeText={setTask} value={task} />
      <Button title='Adcionar' onPress={add} />
      <FlatList data={tasks} renderItem={({ item }) => <Text>{item}</Text>} />
    </View>
  );
};

export default Tasks;
