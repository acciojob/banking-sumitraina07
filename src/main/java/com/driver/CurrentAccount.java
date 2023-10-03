package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance,5000);
        this.tradeLicenseId = tradeLicenseId;

        if(balance < 5000){
            throw new Exception("Insufficient Balance");
        }
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        //first checking the validity

        if(!isLicenseNumberValid(tradeLicenseId)){

            //need to generate valid string

            //throw a validation if cannot create licenseId
            if(!canCreateLicense(tradeLicenseId)){

                throw new Exception("Valid License can not be generated");
            }

            //if we can create then we call rearrange
            System.out.println(rearrangeLicenseId(tradeLicenseId));

        }
        else{
            this.tradeLicenseId = tradeLicenseId;
        }
    }

    public boolean canCreateLicense(String licenseId){

        int[] freq = new int[26];

        char maxFreqChar = '0';
        int maxFreq = 0;

        for(int i=0; i<licenseId.length(); i++){
            char ch = licenseId.charAt(i);
            freq[ch - 'A']++;

            if(freq[ch-'A'] > maxFreq){
                maxFreqChar = ch;
                maxFreq = freq[ch - 'A'];
            }
        }
        if(freq[maxFreqChar] > (licenseId.length() + 1)/2){
            return false;
        }
        return true;
    }

    public String rearrangeLicenseId(String s){

        //finding freq of chars
        int[] freq = new int[26];

        char maxFreqChar = '0';
        int maxFreq = 0;

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            freq[ch - 'A']++;

            if(freq[ch-'A'] > maxFreq){
                maxFreqChar = ch;
                maxFreq = freq[ch - 'A'];
            }
        }
        // put in the result string
        char[] result = new char[s.length()];

        int index = 0;
        //put on even places frst (maxFreqChar)
        while(freq[maxFreqChar - 'A'] > 0){

            result[index] = maxFreqChar;
            index += 2; //only even places
            freq[maxFreqChar]--;
        }
        //Iterate over all the remaining characters

        for(int i=0; i<27;i++){
            int frequency = freq[i]; //get freq of each element

            while(frequency > 0){
                //keep adding to the result
                if(index >= s.length()){
                    index = 1;
                }

                result[index] = (char)(i+'A');
                index +=2; //for all odd places

            }
        }
        String str = String.valueOf(result);
        return str;
    }

    public boolean isLicenseNumberValid(String licenseId){

        for(int i=0;i<licenseId.length();i++){
            if(licenseId.charAt(i) == licenseId.charAt(i+1)){
                return false;
            }
        }
        return true;
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }
}
