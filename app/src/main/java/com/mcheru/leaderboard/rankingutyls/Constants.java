package com.mcheru.leaderboard.rankingutyls;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public  static final String  gadsurl= "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/viewform";
    public static final String url = //"https://docs.google.com/forms/d/e/1FAIpQLSf7FNRfbwfK8Hv-_Z_mulnFg7Wnki6Ua1U4wg_0UmW0NMw_6A/viewform?usp=sf_link";
            " https://docs.google.com/forms/d/e/1FAIpQLSfGD7-klepoq3PN0IgVMYeqSZu-23MGWP2kPWdlNdhrfq_anw/viewform?usp=sf_link";
    // Google Form's Column ID
    public static final String nameField ="entry.2005620554";
    public static final String mailField ="entry.1045781291";
    public static final String addressField ="entry.1065046570";
    public static final String linkField ="entry.1166974658";
    //public static final String commentField ="entry.839337160";

    public static final String EmailAddress = "entry.1824927963";
    public static final String Name = "entry.1877115667";
    public static final String LastName = "entry.2006916086";
    public static final String Linkproject = "entry.284483984";

    public static Map<String, String> setparam(String name, String mail, String address, String link){
        Map<String, String> params = new HashMap<>();
//        params.put(Constants.nameField, name);
//        params.put(Constants.mailField, mail);
//        params.put(Constants.addressField, address);
//        params.put(Constants.linkField, link);
        params.put(Constants.Name, name);
        params.put(Constants.EmailAddress, mail);
        params.put(Constants.LastName, address);
        params.put(Constants.Linkproject, link);
        return params;
    }
}



//
//public class Constants {
//   public static final String url = "https://docs.google.com/forms/d/e/1FAIpQLSfGD7-klepoq3PN0IgVMYeqSZu-23MGWP2kPWdlNdhrfq_anw/viewform";
//        //"https://docs.google.com/forms/d/e/1FAIpQLSf7FNRfbwfK8Hv-_Z_mulnFg7Wnki6Ua1U4wg_0UmW0NMw_6A/viewform?usp=sf_link";
//   // https://docs.google.com/forms/d/e/1FAIpQLSfGD7-klepoq3PN0IgVMYeqSZu-23MGWP2kPWdlNdhrfq_anw/viewform?usp=sf_link";
//    // Google Form's Column ID
//    public static final String nameField ="entry.2005620554";
//    public static final String mailField ="entry.1045781291";
//    public static final String addressField ="entry.1065046570";
//    public static final String linkField ="entry.1166974658";
//    //public static final String commentField ="entry.839337160";
//}
