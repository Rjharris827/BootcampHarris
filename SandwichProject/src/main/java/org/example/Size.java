package org.example;


    public enum Size {
        FOUR(4), EIGHT(8), TWELVE(12);

        private final int inches;

        Size(int inches) {
            this.inches = inches;
        }

        public int getInches() {
            return inches;
        }

        public static Size fromInt(int i) {
            return switch (i) {
                case 4 -> FOUR;
                case 8 -> EIGHT;
                case 12 -> TWELVE;
                default -> throw new IllegalArgumentException("Invalid size.");
            };
        }
    }

