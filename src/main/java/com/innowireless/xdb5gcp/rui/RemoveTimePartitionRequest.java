// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: xdb_5gcp_rui.proto

package com.innowireless.xdb5gcp.rui;

/**
 * Protobuf type {@code xdb_5gcp.rui.RemoveTimePartitionRequest}
 */
public  final class RemoveTimePartitionRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:xdb_5gcp.rui.RemoveTimePartitionRequest)
    RemoveTimePartitionRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use RemoveTimePartitionRequest.newBuilder() to construct.
  private RemoveTimePartitionRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RemoveTimePartitionRequest() {
    tableName_ = "";
    startDatetime_ = "";
    endDatetime_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private RemoveTimePartitionRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            tableName_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            startDatetime_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            endDatetime_ = s;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.innowireless.xdb5gcp.rui.RuiProto.internal_static_xdb_5gcp_rui_RemoveTimePartitionRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.innowireless.xdb5gcp.rui.RuiProto.internal_static_xdb_5gcp_rui_RemoveTimePartitionRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest.class, com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest.Builder.class);
  }

  public static final int TABLE_NAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object tableName_;
  /**
   * <code>string table_name = 1;</code>
   */
  public java.lang.String getTableName() {
    java.lang.Object ref = tableName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      tableName_ = s;
      return s;
    }
  }
  /**
   * <code>string table_name = 1;</code>
   */
  public com.google.protobuf.ByteString
      getTableNameBytes() {
    java.lang.Object ref = tableName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      tableName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int START_DATETIME_FIELD_NUMBER = 2;
  private volatile java.lang.Object startDatetime_;
  /**
   * <pre>
   * start date &lt;= target file &lt;= end date
   * currently, only date unit will be used
   * 2021-04-04T20:11:11 -&gt; 2021-04-04
   * </pre>
   *
   * <code>string start_datetime = 2;</code>
   */
  public java.lang.String getStartDatetime() {
    java.lang.Object ref = startDatetime_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      startDatetime_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * start date &lt;= target file &lt;= end date
   * currently, only date unit will be used
   * 2021-04-04T20:11:11 -&gt; 2021-04-04
   * </pre>
   *
   * <code>string start_datetime = 2;</code>
   */
  public com.google.protobuf.ByteString
      getStartDatetimeBytes() {
    java.lang.Object ref = startDatetime_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      startDatetime_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int END_DATETIME_FIELD_NUMBER = 3;
  private volatile java.lang.Object endDatetime_;
  /**
   * <pre>
   * local datetime format (YYYY-MM-DD)
   * </pre>
   *
   * <code>string end_datetime = 3;</code>
   */
  public java.lang.String getEndDatetime() {
    java.lang.Object ref = endDatetime_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      endDatetime_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * local datetime format (YYYY-MM-DD)
   * </pre>
   *
   * <code>string end_datetime = 3;</code>
   */
  public com.google.protobuf.ByteString
      getEndDatetimeBytes() {
    java.lang.Object ref = endDatetime_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      endDatetime_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getTableNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, tableName_);
    }
    if (!getStartDatetimeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, startDatetime_);
    }
    if (!getEndDatetimeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, endDatetime_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getTableNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, tableName_);
    }
    if (!getStartDatetimeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, startDatetime_);
    }
    if (!getEndDatetimeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, endDatetime_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest)) {
      return super.equals(obj);
    }
    com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest other = (com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest) obj;

    boolean result = true;
    result = result && getTableName()
        .equals(other.getTableName());
    result = result && getStartDatetime()
        .equals(other.getStartDatetime());
    result = result && getEndDatetime()
        .equals(other.getEndDatetime());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + TABLE_NAME_FIELD_NUMBER;
    hash = (53 * hash) + getTableName().hashCode();
    hash = (37 * hash) + START_DATETIME_FIELD_NUMBER;
    hash = (53 * hash) + getStartDatetime().hashCode();
    hash = (37 * hash) + END_DATETIME_FIELD_NUMBER;
    hash = (53 * hash) + getEndDatetime().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code xdb_5gcp.rui.RemoveTimePartitionRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:xdb_5gcp.rui.RemoveTimePartitionRequest)
      com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.innowireless.xdb5gcp.rui.RuiProto.internal_static_xdb_5gcp_rui_RemoveTimePartitionRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.innowireless.xdb5gcp.rui.RuiProto.internal_static_xdb_5gcp_rui_RemoveTimePartitionRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest.class, com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest.Builder.class);
    }

    // Construct using com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      tableName_ = "";

      startDatetime_ = "";

      endDatetime_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.innowireless.xdb5gcp.rui.RuiProto.internal_static_xdb_5gcp_rui_RemoveTimePartitionRequest_descriptor;
    }

    public com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest getDefaultInstanceForType() {
      return com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest.getDefaultInstance();
    }

    public com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest build() {
      com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest buildPartial() {
      com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest result = new com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest(this);
      result.tableName_ = tableName_;
      result.startDatetime_ = startDatetime_;
      result.endDatetime_ = endDatetime_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest) {
        return mergeFrom((com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest other) {
      if (other == com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest.getDefaultInstance()) return this;
      if (!other.getTableName().isEmpty()) {
        tableName_ = other.tableName_;
        onChanged();
      }
      if (!other.getStartDatetime().isEmpty()) {
        startDatetime_ = other.startDatetime_;
        onChanged();
      }
      if (!other.getEndDatetime().isEmpty()) {
        endDatetime_ = other.endDatetime_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object tableName_ = "";
    /**
     * <code>string table_name = 1;</code>
     */
    public java.lang.String getTableName() {
      java.lang.Object ref = tableName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        tableName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string table_name = 1;</code>
     */
    public com.google.protobuf.ByteString
        getTableNameBytes() {
      java.lang.Object ref = tableName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        tableName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string table_name = 1;</code>
     */
    public Builder setTableName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      tableName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string table_name = 1;</code>
     */
    public Builder clearTableName() {
      
      tableName_ = getDefaultInstance().getTableName();
      onChanged();
      return this;
    }
    /**
     * <code>string table_name = 1;</code>
     */
    public Builder setTableNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      tableName_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object startDatetime_ = "";
    /**
     * <pre>
     * start date &lt;= target file &lt;= end date
     * currently, only date unit will be used
     * 2021-04-04T20:11:11 -&gt; 2021-04-04
     * </pre>
     *
     * <code>string start_datetime = 2;</code>
     */
    public java.lang.String getStartDatetime() {
      java.lang.Object ref = startDatetime_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        startDatetime_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * start date &lt;= target file &lt;= end date
     * currently, only date unit will be used
     * 2021-04-04T20:11:11 -&gt; 2021-04-04
     * </pre>
     *
     * <code>string start_datetime = 2;</code>
     */
    public com.google.protobuf.ByteString
        getStartDatetimeBytes() {
      java.lang.Object ref = startDatetime_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        startDatetime_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * start date &lt;= target file &lt;= end date
     * currently, only date unit will be used
     * 2021-04-04T20:11:11 -&gt; 2021-04-04
     * </pre>
     *
     * <code>string start_datetime = 2;</code>
     */
    public Builder setStartDatetime(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      startDatetime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * start date &lt;= target file &lt;= end date
     * currently, only date unit will be used
     * 2021-04-04T20:11:11 -&gt; 2021-04-04
     * </pre>
     *
     * <code>string start_datetime = 2;</code>
     */
    public Builder clearStartDatetime() {
      
      startDatetime_ = getDefaultInstance().getStartDatetime();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * start date &lt;= target file &lt;= end date
     * currently, only date unit will be used
     * 2021-04-04T20:11:11 -&gt; 2021-04-04
     * </pre>
     *
     * <code>string start_datetime = 2;</code>
     */
    public Builder setStartDatetimeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      startDatetime_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object endDatetime_ = "";
    /**
     * <pre>
     * local datetime format (YYYY-MM-DD)
     * </pre>
     *
     * <code>string end_datetime = 3;</code>
     */
    public java.lang.String getEndDatetime() {
      java.lang.Object ref = endDatetime_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        endDatetime_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * local datetime format (YYYY-MM-DD)
     * </pre>
     *
     * <code>string end_datetime = 3;</code>
     */
    public com.google.protobuf.ByteString
        getEndDatetimeBytes() {
      java.lang.Object ref = endDatetime_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        endDatetime_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * local datetime format (YYYY-MM-DD)
     * </pre>
     *
     * <code>string end_datetime = 3;</code>
     */
    public Builder setEndDatetime(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      endDatetime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * local datetime format (YYYY-MM-DD)
     * </pre>
     *
     * <code>string end_datetime = 3;</code>
     */
    public Builder clearEndDatetime() {
      
      endDatetime_ = getDefaultInstance().getEndDatetime();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * local datetime format (YYYY-MM-DD)
     * </pre>
     *
     * <code>string end_datetime = 3;</code>
     */
    public Builder setEndDatetimeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      endDatetime_ = value;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:xdb_5gcp.rui.RemoveTimePartitionRequest)
  }

  // @@protoc_insertion_point(class_scope:xdb_5gcp.rui.RemoveTimePartitionRequest)
  private static final com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest();
  }

  public static com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RemoveTimePartitionRequest>
      PARSER = new com.google.protobuf.AbstractParser<RemoveTimePartitionRequest>() {
    public RemoveTimePartitionRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new RemoveTimePartitionRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RemoveTimePartitionRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RemoveTimePartitionRequest> getParserForType() {
    return PARSER;
  }

  public com.innowireless.xdb5gcp.rui.RemoveTimePartitionRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

