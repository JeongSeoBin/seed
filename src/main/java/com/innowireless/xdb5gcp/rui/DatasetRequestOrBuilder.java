// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: xdb_5gcp_rui.proto

package com.innowireless.xdb5gcp.rui;

public interface DatasetRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:xdb_5gcp.rui.DatasetRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string table_name = 1;</code>
   */
  java.lang.String getTableName();
  /**
   * <code>string table_name = 1;</code>
   */
  com.google.protobuf.ByteString
      getTableNameBytes();

  /**
   * <code>int64 record_offset = 2;</code>
   */
  long getRecordOffset();

  /**
   * <code>int64 record_count = 3;</code>
   */
  long getRecordCount();
}