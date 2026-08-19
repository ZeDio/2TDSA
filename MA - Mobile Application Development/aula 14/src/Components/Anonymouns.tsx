import React, { useState } from "react";
import { auth } from "../firebaseConfig";
import * as firebase from "firebase/auth";
import { Text, View, Button } from "react-native";

const Anonymouns = () => {
  const [user, setUser] = useState<firebase.User>();

  const doAnonymousLogin = async () => {
    const { user } = await firebase.signInAnonymously(auth);
    setUser(user);
  };

  return (
    <View style={{ flex: 1, justifyContent: "center" }}>
      {!user ? (
        <Button title="Login Anônimo" onPress={doAnonymousLogin} />
      ) : (
        <Text>User ID: {user.uid}</Text>
      )}
    </View>
  );
};

export default Anonymouns;
