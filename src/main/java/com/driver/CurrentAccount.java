package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance,5000);
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        if(!isNumberValid(tradeLicenseId)){

            String rearrangedId = arrangeString(tradeLicenseId);
            if(rearrangedId == ""){
                throw new Exception("Valid License can not be generated");
            }
            else {
                this.tradeLicenseId = rearrangedId;
            }
        }
    }

    public char getCountChar(int[] count){

        int max = 0;
        char ch = 0;
        for(int i=0; i<26;i++){
            if(count[i] > max){
                max = count[i];
                ch = (char)((int)'A' + i);
            }
        }
        return ch;
    }

    private String arrangeString(String S) {

        int N = S.length();

        int[] count = new int[26];
        for(int i=0; i<26; i++){
            count[i] = 0;
        }
        for(char ch : S.toCharArray()){
            count[(int) ch - (int) 'A']++;
        }


        char ch_max = getCountChar(count);
        int maxCount = count[(int) ch_max- (int) 'A'];

        if(maxCount > (N+1)/2){
            return "";
        }
        String res = "";
        for(int i=0; i<N; i++){
            res += ' ';
        }

        int ind = 0;
        while(maxCount > 0){
            res = res.substring(0, ind) + ch_max
                    + res.substring(ind + 1);

            ind = ind + 2;
            maxCount--;
        }
        count[(int)ch_max - (int)'A'] = 0;
        for(int i=0; i<26; i++){
            while(count[i] > 0){
                ind = (ind>=N) ? 1 : ind;
                res = res.substring(0, ind)
                        + (char) ((int)'A' + 1)
                        + res.substring(ind + 1);
                ind = ind + 2;
                count[i]--;
            }
        }
        return res;
    }

    private boolean isNumberValid(String licenseId) {
        for(int i=0; i < licenseId.length() - 1; i++){
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
