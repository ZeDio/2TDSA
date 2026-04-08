import { StatusBar } from "react-native";
import Tasks from "./src/Components/index";
import Details from "./src/Components/Details";
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { Home } from "./src/types/navigation";

const Stack = createNativeStackNavigator<Home>();

export default function App() {
  return (
    <>
      <NavigationContainer>
        <Stack.Navigator>
          <Stack.Screen name="List" component={Tasks} />
          <Stack.Screen name="Details" component={Details} />
        </Stack.Navigator>
      </NavigationContainer>
    </>
  );
}
