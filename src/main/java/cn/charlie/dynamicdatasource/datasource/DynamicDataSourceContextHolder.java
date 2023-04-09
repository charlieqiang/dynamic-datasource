package cn.charlie.dynamicdatasource.datasource;

/**
 * @author charlie
 * @date 4/9/2023 4:00 PM
 **/
public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> CONTEXTHOLDER = new ThreadLocal();

    public DynamicDataSourceContextHolder() {
    }

    public static void setDataSourceType(String dataSourceType) {
        CONTEXTHOLDER.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return CONTEXTHOLDER.get();
    }

    public static void clearDataSourceType() {
        CONTEXTHOLDER.remove();
    }
}
