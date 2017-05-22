package com.avparamonov.cardvalidator.controllers;

/**
 * Public REST API for work with application.
 *
 * Created by AVParamonov on 17.05.17.
 */
public interface Api {

    String ROOT_PATH = "/validator";

    interface V1 {
        String VERSION = "/v1";
        String VALIDATE = VERSION + "/validate";
    }
}
