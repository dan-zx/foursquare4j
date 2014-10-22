package com.foursquare4j.response;

import java.util.Arrays;

public class Phrase extends Count {

    public static class SampleTip {

        public static class Entity {

            private Integer[] indices;
            private String type;

            public Integer[] getIndices() {
                return indices;
            }

            protected void setIndices(Integer[] indices) {
                this.indices = indices;
            }

            public String getType() {
                return type;
            }

            protected void setType(String type) {
                this.type = type;
            }

            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + Arrays.hashCode(indices);
                result = prime * result + ((type == null) ? 0 : type.hashCode());
                return result;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null) return false;
                if (getClass() != obj.getClass()) return false;
                Entity other = (Entity) obj;
                if (!Arrays.equals(indices, other.indices)) return false;
                if (type == null) {
                    if (other.type != null) return false;
                } else if (!type.equals(other.type)) return false;
                return true;
            }

            @Override
            public String toString() {
                StringBuilder builder = new StringBuilder();
                builder.append("Entity [indices=").append(Arrays.toString(indices)).append(", type=").append(type).append("]");
                return builder.toString();
            }
        }

        private Entity[] entities;
        private String text;

        public Entity[] getEntities() {
            return entities;
        }

        protected void setEntities(Entity[] entities) {
            this.entities = entities;
        }

        public String getText() {
            return text;
        }

        protected void setText(String text) {
            this.text = text;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Arrays.hashCode(entities);
            result = prime * result + ((text == null) ? 0 : text.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            SampleTip other = (SampleTip) obj;
            if (!Arrays.equals(entities, other.entities)) return false;
            if (text == null) {
                if (other.text != null) return false;
            } else if (!text.equals(other.text)) return false;
            return true;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("SampleTip [entities=").append(Arrays.toString(entities)).append(", text=").append(text).append("]");
            return builder.toString();
        }
    }

    private String phrase;
    private SampleTip sample;

    public String getPhrase() {
        return phrase;
    }

    protected void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public SampleTip getSample() {
        return sample;
    }

    protected void setSample(SampleTip sample) {
        this.sample = sample;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((phrase == null) ? 0 : phrase.hashCode());
        result = prime * result + ((sample == null) ? 0 : sample.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        Phrase other = (Phrase) obj;
        if (phrase == null) {
            if (other.phrase != null) return false;
        } else if (!phrase.equals(other.phrase)) return false;
        if (sample == null) {
            if (other.sample != null) return false;
        } else if (!sample.equals(other.sample)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Phrase [phrase=").append(phrase).append(", sample=").append(sample).append(", count=").append(getCount()).append("]");
        return builder.toString();
    }
}