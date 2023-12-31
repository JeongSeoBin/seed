// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: xdb_5gcp_rui.proto

package com.innowireless.xdb5gcp.rui;

/**
 * Protobuf enum {@code xdb_5gcp.rui.SqlType}
 */
public enum SqlType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>SELECT_STMT = 0;</code>
   */
  SELECT_STMT(0),
  /**
   * <code>CREATE_STMT = 1;</code>
   */
  CREATE_STMT(1),
  /**
   * <code>DROP_STMT = 2;</code>
   */
  DROP_STMT(2),
  /**
   * <code>INSERT_STMT = 3;</code>
   */
  INSERT_STMT(3),
  /**
   * <code>IMPORT_STMT = 4;</code>
   */
  IMPORT_STMT(4),
  /**
   * <code>EXPORT_STMT = 5;</code>
   */
  EXPORT_STMT(5),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>SELECT_STMT = 0;</code>
   */
  public static final int SELECT_STMT_VALUE = 0;
  /**
   * <code>CREATE_STMT = 1;</code>
   */
  public static final int CREATE_STMT_VALUE = 1;
  /**
   * <code>DROP_STMT = 2;</code>
   */
  public static final int DROP_STMT_VALUE = 2;
  /**
   * <code>INSERT_STMT = 3;</code>
   */
  public static final int INSERT_STMT_VALUE = 3;
  /**
   * <code>IMPORT_STMT = 4;</code>
   */
  public static final int IMPORT_STMT_VALUE = 4;
  /**
   * <code>EXPORT_STMT = 5;</code>
   */
  public static final int EXPORT_STMT_VALUE = 5;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static SqlType valueOf(int value) {
    return forNumber(value);
  }

  public static SqlType forNumber(int value) {
    switch (value) {
      case 0: return SELECT_STMT;
      case 1: return CREATE_STMT;
      case 2: return DROP_STMT;
      case 3: return INSERT_STMT;
      case 4: return IMPORT_STMT;
      case 5: return EXPORT_STMT;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<SqlType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      SqlType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<SqlType>() {
          public SqlType findValueByNumber(int number) {
            return SqlType.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return com.innowireless.xdb5gcp.rui.RuiProto.getDescriptor().getEnumTypes().get(0);
  }

  private static final SqlType[] VALUES = values();

  public static SqlType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private SqlType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:xdb_5gcp.rui.SqlType)
}

