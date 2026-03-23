import MySectionList from "./src/Components/MySectionList";
import MyActivityIndicator from "./src/Components/MyActivityIndicator";
import MyAlert from "./src/Components/MyAlert";
import MyAnimation from "./src/Components/MyAnimation";
import MyLink from "./src/Components/MyLink";
import MyModal from "./src/Components/MyModal";
import MyPixelRatio from "./src/Components/MyModal";

export default function App() {
  return (
    <View style={{ paddingTop: 40 }}>
      <MySectionList/>
      <MyActivityIndicator/>
      <MyAlert/>
      <MyAnimation/>
      <MyLink/>
      <MyModal/>
      <MyPixelRatio/>
    </View>
  );
}