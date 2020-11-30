import * as React from 'react';
import { StyleSheet, View, Text } from 'react-native';
import BharatxReactnativeSimple from '@bharatx/bharatx-reactnative-simple';
import BharatxReactnativeSecurityhelpers from '@bharatx/bharatx-reactnative-securityhelpers';

export default function App() {
  const [result, setResult] = React.useState<number | undefined>();

  React.useEffect(() => {
    (async () => {
      await BharatxReactnativeSimple.initialize(
        'testSimplePartnerId',
        'testSimpleApiKey'
      );
      BharatxReactnativeSecurityhelpers.storeThemeColorPreferenceInHex("#000000");
      BharatxReactnativeSimple.startTransaction(
        null,
        null,
        '+919790468714',
        1000,
        null,
        (event) => {
          console.log(event);
        }
      );
      setResult(1);
    })();
  }, []);

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
