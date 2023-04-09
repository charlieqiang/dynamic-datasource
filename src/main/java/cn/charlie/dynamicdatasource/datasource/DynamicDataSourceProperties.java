package cn.charlie.dynamicdatasource.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.Map;

/**
 * @author charlie
 * @date 4/9/2023 4:00 PM
 **/
@ConfigurationProperties(
        prefix = "spring.datasource.dynamic"
)
public class DynamicDataSourceProperties {
    private String primary;
    private Map<String, DataSourceProperty> datasource;

    public DynamicDataSourceProperties() {
    }

    public String getPrimary() {
        return this.primary;
    }

    public Map<String, DynamicDataSourceProperties.DataSourceProperty> getDatasource() {
        return this.datasource;
    }

    public void setPrimary(final String primary) {
        this.primary = primary;
    }

    public void setDatasource(final Map<String, DynamicDataSourceProperties.DataSourceProperty> datasource) {
        this.datasource = datasource;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof DynamicDataSourceProperties)) {
            return false;
        } else {
            DynamicDataSourceProperties other = (DynamicDataSourceProperties) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$primary = this.getPrimary();
                Object other$primary = other.getPrimary();
                if (this$primary == null) {
                    if (other$primary != null) {
                        return false;
                    }
                } else if (!this$primary.equals(other$primary)) {
                    return false;
                }

                Object this$datasource = this.getDatasource();
                Object other$datasource = other.getDatasource();
                if (this$datasource == null) {
                    if (other$datasource != null) {
                        return false;
                    }
                } else if (!this$datasource.equals(other$datasource)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DynamicDataSourceProperties;
    }

    public int hashCode() {
        Object $primary = this.getPrimary();
        int result = 59 + ($primary == null ? 43 : $primary.hashCode());
        Object $datasource = this.getDatasource();
        result = result * 59 + ($datasource == null ? 43 : $datasource.hashCode());
        return result;
    }

    public String toString() {
        return "DynamicDataSourceProperties(primary=" + this.getPrimary() + ", datasource=" + this.getDatasource() + ")";
    }

    protected static class DataSourceProperty {
        private String driverClassName;
        private String url;
        private String username;
        private String password;

        public DataSourceProperty() {
        }

        public String getDriverClassName() {
            return this.driverClassName;
        }

        public String getUrl() {
            return this.url;
        }

        public String getUsername() {
            return this.username;
        }

        public String getPassword() {
            return this.password;
        }

        public void setDriverClassName(final String driverClassName) {
            this.driverClassName = driverClassName;
        }

        public void setUrl(final String url) {
            this.url = url;
        }

        public void setUsername(final String username) {
            this.username = username;
        }

        public void setPassword(final String password) {
            this.password = password;
        }

        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof DynamicDataSourceProperties.DataSourceProperty)) {
                return false;
            } else {
                DynamicDataSourceProperties.DataSourceProperty other = (DynamicDataSourceProperties.DataSourceProperty) o;
                if (!other.canEqual(this)) {
                    return false;
                } else {
                    Object this$driverClassName = this.getDriverClassName();
                    Object other$driverClassName = other.getDriverClassName();
                    if (this$driverClassName == null) {
                        if (other$driverClassName != null) {
                            return false;
                        }
                    } else if (!this$driverClassName.equals(other$driverClassName)) {
                        return false;
                    }

                    label57:
                    {
                        Object this$url = this.getUrl();
                        Object other$url = other.getUrl();
                        if (this$url == null) {
                            if (other$url == null) {
                                break label57;
                            }
                        } else if (this$url.equals(other$url)) {
                            break label57;
                        }

                        return false;
                    }

                    Object this$username = this.getUsername();
                    Object other$username = other.getUsername();
                    if (this$username == null) {
                        if (other$username != null) {
                            return false;
                        }
                    } else if (!this$username.equals(other$username)) {
                        return false;
                    }

                    Object this$password = this.getPassword();
                    Object other$password = other.getPassword();
                    if (this$password == null) {
                        if (other$password == null) {
                            return true;
                        }
                    } else if (this$password.equals(other$password)) {
                        return true;
                    }

                    return false;
                }
            }
        }

        protected boolean canEqual(final Object other) {
            return other instanceof DynamicDataSourceProperties.DataSourceProperty;
        }

        public int hashCode() {
            int result = 59 + 43;
            Object $driverClassName = this.getDriverClassName();
            result = result * 59 + ($driverClassName == null ? 43 : $driverClassName.hashCode());
            Object $url = this.getUrl();
            result = result * 59 + ($url == null ? 43 : $url.hashCode());
            Object $username = this.getUsername();
            result = result * 59 + ($username == null ? 43 : $username.hashCode());
            Object $password = this.getPassword();
            result = result * 59 + ($password == null ? 43 : $password.hashCode());
            return result;
        }

        public String toString() {
            return "DynamicDataSourceProperties.DataSourceProperty(driverClassName=" + this.getDriverClassName() + ", url=" + this.getUrl() + ", username=" + this.getUsername() + ", password=" + this.getPassword() + ")";
        }
    }
}
