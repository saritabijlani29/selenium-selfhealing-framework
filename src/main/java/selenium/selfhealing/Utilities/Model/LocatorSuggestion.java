    package selenium.selfhealing.Utilities.Model;

    public class LocatorSuggestion {
        private String id;
        private String name;
        private String xpath;
        private String cssSelector;
        private String className;
        private String linkText;

        public String getID() {
            return id;
        }

        public void setID(String value) {
            this.id = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String value) {
            this.name = value;
        }

        public String getXpath() {
            return xpath;
        }

        public void setXpath(String value) {
            this.xpath = value;
        }

        public String getCSSSelector() {
            return cssSelector;
        }

        public void setCSSSelector(String value) {
            this.cssSelector = value;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String value) {
            this.className = value;
        }

        public String getLinkText() {
            return linkText;
        }

        public void setLinkText(String value) {
            this.linkText = value;
        }
    }