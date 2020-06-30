package com.company.core;

public enum FunctionType {
    FIRST{
        @Override
        public String toString() {
            return "y'=4sin(x)-y";
        }
    },
    SECOND{
        @Override
        public String toString() {
            return "y'=x-y";
        }
    },
    THIRD{
        @Override
        public String toString() {
            return "y'=cos(x)-y";
        }
    }

}
