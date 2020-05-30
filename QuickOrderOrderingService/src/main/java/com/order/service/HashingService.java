package com.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * Service to hash passwords with salt from "application.properties".
 */
@Service
public class HashingService {

  private static final Logger log = LoggerFactory.getLogger(HashingService.class);

  private final String passwordSalt;

  public HashingService(@Value("${security.password.salt}") String passwordSalt) {
    this.passwordSalt = passwordSalt;
    if (passwordSalt == null || passwordSalt.isEmpty()) {
      throw new IllegalStateException("Please set salt to 'security.password.salt' property");
    }
  }

  /**
   * Calculates salted SHA-1 of a given password.
   */
  public String hash(String password) {
    try {
      MessageDigest crypt = MessageDigest.getInstance("SHA-1");
      crypt.reset();
      crypt.update(passwordSalt.getBytes(StandardCharsets.UTF_8));
      crypt.update(password.getBytes(StandardCharsets.UTF_8));
      return byteToHex(crypt.digest());
    } catch (NoSuchAlgorithmException e) {
      log.error("Error while calculating SHA-1", e);
      throw new RuntimeException(e);
    }
  }

  private static String byteToHex(final byte[] hash) {
    Formatter formatter = new Formatter();
    for (byte b : hash) {
      formatter.format("%02x", b);
    }
    String result = formatter.toString();
    formatter.close();
    return result;
  }
}
