import { NativeModules } from 'react-native';

type BharatxReactnativeSimpleType = {
  initialize(partnerId: string, partnerApiKey: String): Promise<void>;
  startTransaction(
    transactionId: string | null,
    userId: string | null,
    phoneNumber: string,
    amountInPaise: number,
    payeeBankDetailsMap: {
      beneficiaryName: string;
      accountNumber: string;
      ifscCode: string;
    } | null,
    transactionCallback: ((event: string) => void) | null
  ): void;
};

const { BharatxReactnativeSimple } = NativeModules;

export default BharatxReactnativeSimple as BharatxReactnativeSimpleType;
