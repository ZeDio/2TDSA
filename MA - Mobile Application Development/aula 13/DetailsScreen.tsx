import { View, Text, Image, ScrollView } from 'react-native';

export default function DetailsScreen({ route }) {
  const { movie } = route.params;

  return (
    <ScrollView style={{ padding: 15 }}>
      <Text style={{ fontSize: 24, fontWeight: 'bold' }}>
        {movie.originalTitle}
      </Text>

      <Image
        source={{ uri: movie.posterUrl }}
        style={{ width: '100%', height: 300, marginVertical: 10 }}
      />

      <Text>Empresa: {movie.company}</Text>
      <Text>Avaliação: {movie.rate}</Text>
      <Text>Duração: {movie.minutes} minutos</Text>
      <Text>Orçamento: ${movie.financialData?.budget}</Text>
      <Text>Faturamento: ${movie.financialData?.gross?.worldwide}</Text>
    </ScrollView>
  );
}