import { useEffect, useState } from 'react';
import { View, Text, FlatList, TouchableOpacity } from 'react-native';

export default function HomeScreen({ navigation }) {
  const [movies, setMovies] = useState([]);

  useEffect(() => {
    fetch('https://6414e8c38dade07073cb2a6a.mockapi.io/api/v1/movies')
      .then(res => res.json())
      .then(data => setMovies(data));
  }, []);

  return (
    <View>
      <FlatList
        data={movies}
        keyExtractor={(item) => item.id}
        renderItem={({ item }) => (
          <TouchableOpacity onPress={() => navigation.navigate('Detalhes', { movie: item })}>
            <Text style={{ padding: 15, fontSize: 18 }}>
              {item.originalTitle}
            </Text>
          </TouchableOpacity>
        )}
      />
    </View>
  );
}