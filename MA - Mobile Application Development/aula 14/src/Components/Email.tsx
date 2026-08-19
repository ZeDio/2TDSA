import { Button, Text, TextInput, View } from "react-native";
import { auth } from "../firebaseConfig";
import * as firebase from "firebase/auth";
import { useEffect, useState } from "react";

const Email = () => {
    const [email, setEmail] = useState<string>("");
    const [password, setPassword] = useState<string>("");
    const [user, setUser] = useState<firebase.User | null>();

    const createAccount = async () => {
        try {
            const { user } = await firebase.createUserWithEmailAndPassword(auth, email, password);
        } catch (error) {
            console.log("P** de erro", error);
        }
    };

    const signOut = async () => {
        await firebase.signOut(auth);
        setUser(undefined);
    };

    useEffect (()=> {
        const subscriber = firebase.onAuthStateChanged(auth, (firebaseUser) =>{
            console.log("User",firebaseUser);
            setUser(firebaseUser || undefined);
        });
        return subscriber
    })

    if (user) {
        return (
            <View>
                <Text>User ID: {user.uid}</Text>
                <Button title='Sign out' onPress={signOut} />
            </View>
        );
    }

    return (
        <View style={{ flex: 1, justifyContent: "center", gap: 10, paddingHorizontal: 20 }}>
            <TextInput placeholder='email' onChangeText={setEmail} />
            <TextInput placeholder='password' onChangeText={setPassword} />
            <Button title='Criar' onPress={createAccount} />
        </View>
    );
};

export default Email;
