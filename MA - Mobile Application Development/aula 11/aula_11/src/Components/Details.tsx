import { NativeStackNavigationProp } from "@react-navigation/native-stack";
import { View, Text, Button } from "react-native";
import { RootStackParamList } from "../../App";
import { RouteProp, useNavigation, useRoute } from "@react-navigation/native";

const Botao = () => {
  const navigation =
    useNavigation<NativeStackNavigationProp<RootStackParamList>>();
  return (
    <Button
      title="Go to details"
      onPress={() => navigation.push("Details", { id: Math.random() })}
    />
  );
};

const Details = () => {
  const navigation =
    useNavigation<NativeStackNavigationProp<RootStackParamList>>();
  const route = useRoute<RouteProp<RootStackParamList, "Details">>();

  return (
    <View>
      <Text>Details: {route.params.id}</Text>
      <Text>Details: {route.params.name}</Text>
      <Botao />
      <Button title="Voltar" onPress={() => navigation.popToTop()} />
    </View>
  );
};

export default Details;