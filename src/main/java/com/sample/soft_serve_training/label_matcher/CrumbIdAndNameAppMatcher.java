package com.sample.soft_serve_training.label_matcher;

public class CrumbIdAndNameAppMatcher {

    public static void main(String[] args) {

        String day = "FRIDAY";

//        int numLetters = switch (day) {
//            case "MONDAY", "FRIDAY", "SUNDAY" -> 6;
//            case "TUESDAY" -> 7;
//            case "THURSDAY", "SATURDAY" -> 8;
//            case "WEDNESDAY" -> 9;
//            default -> throw new IllegalStateException("Huh? " + day);
//        };

        System.out.println("Hello");
    }

    public static String findCrumbId(String appName) {
        try {
            return CrumbIdAndApiNameEnum.valueOf(appName.replaceAll("-", "_")).getCrumbId();
        } catch (Exception ex) {
            return null;
        }
    }

    public static String findApiMatchName(String appName) {
        try {
            return CrumbIdAndApiNameEnum.valueOf(appName.replaceAll("-", "_")).getApiName();
        } catch (Exception ex) {
            return null;
        }
    }

    private enum CrumbIdAndApiNameEnum {
        // IDs need to be matched with specification from McKesson
        sd_fdb_sftp_system_api("1", "FDB System API"),
        sd_compass_system_api("2", "Compass System API"),
        sd_product_process_api("3", "Product Process API"),
        sd_lsp_system_api("4", "LSP System API"),
        sd_order_experience_api("5", "Order Experience API"),
        sd_order_process_api("6", "Order Process API"),
        sd_remtp_system_api("7", "REMTP System API")
        ;

        private String crumbId;
        private String apiName;


        CrumbIdAndApiNameEnum(String crumbId, String apiName) {
            this.crumbId = crumbId;
            this.apiName = apiName;
        }

        private String getCrumbId() {
            return crumbId;
        }

        private String getApiName() {
            return apiName;
        }
    }
}
