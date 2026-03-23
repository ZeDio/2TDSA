namespace LinqFaroShuffle;

public static class CardExtentions
{
    extension<T>(IEnumerable<T> sequence)
    {
        public IEnumerable<T> InterleaveSequenceWith(IEnumerable<T> second)
        {
            var firstIter = sequence.GetEnumerator();
            var secondIter = second.GetEnumerator();

            while (firstIter.MoveNext() && secondIter.MoveNext())
            {
                yield return firstIter.Current;
                yield return secondIter.Current;
            }
        }
        
        public bool SequenceEquals(IEnumerable<T> second)
        {
            var firstIter = sequence.GetEnumerator();
            var secondIter = second.GetEnumerator();

            while ((firstIter?.MoveNext() == true) && secondIter.MoveNext())
            {
                if ((firstIter.Current is not null) && !firstIter.Current.Equals(secondIter.Current))
                {
                    return false;
                }
            }

            return true;
        }
    }
}