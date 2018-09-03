package news.factory.com.model;

    public class Breadcrumbs
    {
        private String category;

        private String parent_category;

        private String parent_category_url;

        private String parent_category_slug;

        private String category_url;

        public String getCategory ()
        {
            return category;
        }

        public void setCategory (String category)
        {
            this.category = category;
        }

        public String getParent_category ()
        {
            return parent_category;
        }

        public void setParent_category (String parent_category)
        {
            this.parent_category = parent_category;
        }

        public String getParent_category_url ()
        {
            return parent_category_url;
        }

        public void setParent_category_url (String parent_category_url)
        {
            this.parent_category_url = parent_category_url;
        }

        public String getParent_category_slug ()
        {
            return parent_category_slug;
        }

        public void setParent_category_slug (String parent_category_slug)
        {
            this.parent_category_slug = parent_category_slug;
        }

        public String getCategory_url ()
        {
            return category_url;
        }

        public void setCategory_url (String category_url)
        {
            this.category_url = category_url;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [category = "+category+", parent_category = "+parent_category+", parent_category_url = "+parent_category_url+", parent_category_slug = "+parent_category_slug+", category_url = "+category_url+"]";
        }
    }
