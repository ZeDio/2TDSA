import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { Button, Text, TextInput, ScrollView } from "react-native";
import { RootStackParamList } from "../../App";

const Login = ({ navigation }: NativeStackScreenProps<RootStackParamList>) => {
  return (
    <ScrollView>
      <Text>User</Text>
      <TextInput placeholder="email" keyboardType="email-address" />
      <Text>Password</Text>
      <TextInput placeholder="password" secureTextEntry={true} />
      <Button
        title="Login"
        onPress={() =>
          navigation.navigate("Details", {
            id: Math.random(),
            name: "Leonardo",
          })
        }
      />
    </ScrollView>
  );
};

export default Login;
