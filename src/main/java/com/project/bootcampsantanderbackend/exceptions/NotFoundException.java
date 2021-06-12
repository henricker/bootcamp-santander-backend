package com.project.bootcampsantanderbackend.exceptions;

import com.project.bootcampsantanderbackend.util.MessageUtil;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super(MessageUtil.NO_RECORDS_FOUND);
    }
}
