// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: xdb_5gcp_rui.proto

package com.innowireless.xdb5gcp.rui;

public interface BaseResultOrBuilder extends
    // @@protoc_insertion_point(interface_extends:xdb_5gcp.rui.BaseResult)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.xdb_5gcp.rui.BaseResult.ResultCode result_code = 1;</code>
   */
  int getResultCodeValue();
  /**
   * <code>.xdb_5gcp.rui.BaseResult.ResultCode result_code = 1;</code>
   */
  com.innowireless.xdb5gcp.rui.BaseResult.ResultCode getResultCode();

  /**
   * <code>string error_message = 2;</code>
   */
  java.lang.String getErrorMessage();
  /**
   * <code>string error_message = 2;</code>
   */
  com.google.protobuf.ByteString
      getErrorMessageBytes();

  /**
   * <code>.xdb_5gcp.rui.BaseResult.SyntaxErrorPos syntax_error_pos = 3;</code>
   */
  boolean hasSyntaxErrorPos();
  /**
   * <code>.xdb_5gcp.rui.BaseResult.SyntaxErrorPos syntax_error_pos = 3;</code>
   */
  com.innowireless.xdb5gcp.rui.BaseResult.SyntaxErrorPos getSyntaxErrorPos();
  /**
   * <code>.xdb_5gcp.rui.BaseResult.SyntaxErrorPos syntax_error_pos = 3;</code>
   */
  com.innowireless.xdb5gcp.rui.BaseResult.SyntaxErrorPosOrBuilder getSyntaxErrorPosOrBuilder();
}
