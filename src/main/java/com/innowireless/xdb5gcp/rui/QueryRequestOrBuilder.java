// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: xdb_5gcp_rui.proto

package com.innowireless.xdb5gcp.rui;

public interface QueryRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:xdb_5gcp.rui.QueryRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string user_identity = 1;</code>
   */
  java.lang.String getUserIdentity();
  /**
   * <code>string user_identity = 1;</code>
   */
  com.google.protobuf.ByteString
      getUserIdentityBytes();

  /**
   * <code>string sql_string = 2;</code>
   */
  java.lang.String getSqlString();
  /**
   * <code>string sql_string = 2;</code>
   */
  com.google.protobuf.ByteString
      getSqlStringBytes();

  /**
   * <pre>
   * start timestamp for ISA_HUB table
   * </pre>
   *
   * <code>int64 start_timestamp = 3;</code>
   */
  long getStartTimestamp();

  /**
   * <pre>
   * end timestamp for ISA_HUB table
   * </pre>
   *
   * <code>int64 end_timestamp = 4;</code>
   */
  long getEndTimestamp();
}
