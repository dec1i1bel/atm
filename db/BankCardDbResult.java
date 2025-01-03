package db;

import enums.ResultStatus;

public record BankCardDbResult(ResultStatus resultStatus, BankCard bankCard) {}
