package news.factory.com.model;

public class CategoryAnalytics {
        private String dotmetrics;

        public String getDotmetrics ()
        {
            return dotmetrics;
        }

        public void setDotmetrics (String dotmetrics)
        {
            this.dotmetrics = dotmetrics;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [dotmetrics = "+dotmetrics+"]";
        }
}

