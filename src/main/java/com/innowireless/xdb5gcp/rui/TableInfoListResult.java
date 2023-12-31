// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: xdb_5gcp_rui.proto

package com.innowireless.xdb5gcp.rui;

/**
 * Protobuf type {@code xdb_5gcp.rui.TableInfoListResult}
 */
public  final class TableInfoListResult extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:xdb_5gcp.rui.TableInfoListResult)
    TableInfoListResultOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TableInfoListResult.newBuilder() to construct.
  private TableInfoListResult(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TableInfoListResult() {
    tableInfoList_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TableInfoListResult(
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
            com.innowireless.xdb5gcp.rui.BaseResult.Builder subBuilder = null;
            if (baseResult_ != null) {
              subBuilder = baseResult_.toBuilder();
            }
            baseResult_ = input.readMessage(com.innowireless.xdb5gcp.rui.BaseResult.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(baseResult_);
              baseResult_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              tableInfoList_ = new java.util.ArrayList<com.innowireless.xdb5gcp.rui.TableInfo>();
              mutable_bitField0_ |= 0x00000002;
            }
            tableInfoList_.add(
                input.readMessage(com.innowireless.xdb5gcp.rui.TableInfo.parser(), extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
        tableInfoList_ = java.util.Collections.unmodifiableList(tableInfoList_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.innowireless.xdb5gcp.rui.RuiProto.internal_static_xdb_5gcp_rui_TableInfoListResult_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.innowireless.xdb5gcp.rui.RuiProto.internal_static_xdb_5gcp_rui_TableInfoListResult_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.innowireless.xdb5gcp.rui.TableInfoListResult.class, com.innowireless.xdb5gcp.rui.TableInfoListResult.Builder.class);
  }

  private int bitField0_;
  public static final int BASE_RESULT_FIELD_NUMBER = 1;
  private com.innowireless.xdb5gcp.rui.BaseResult baseResult_;
  /**
   * <code>.xdb_5gcp.rui.BaseResult base_result = 1;</code>
   */
  public boolean hasBaseResult() {
    return baseResult_ != null;
  }
  /**
   * <code>.xdb_5gcp.rui.BaseResult base_result = 1;</code>
   */
  public com.innowireless.xdb5gcp.rui.BaseResult getBaseResult() {
    return baseResult_ == null ? com.innowireless.xdb5gcp.rui.BaseResult.getDefaultInstance() : baseResult_;
  }
  /**
   * <code>.xdb_5gcp.rui.BaseResult base_result = 1;</code>
   */
  public com.innowireless.xdb5gcp.rui.BaseResultOrBuilder getBaseResultOrBuilder() {
    return getBaseResult();
  }

  public static final int TABLE_INFO_LIST_FIELD_NUMBER = 2;
  private java.util.List<com.innowireless.xdb5gcp.rui.TableInfo> tableInfoList_;
  /**
   * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
   */
  public java.util.List<com.innowireless.xdb5gcp.rui.TableInfo> getTableInfoListList() {
    return tableInfoList_;
  }
  /**
   * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
   */
  public java.util.List<? extends com.innowireless.xdb5gcp.rui.TableInfoOrBuilder> 
      getTableInfoListOrBuilderList() {
    return tableInfoList_;
  }
  /**
   * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
   */
  public int getTableInfoListCount() {
    return tableInfoList_.size();
  }
  /**
   * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
   */
  public com.innowireless.xdb5gcp.rui.TableInfo getTableInfoList(int index) {
    return tableInfoList_.get(index);
  }
  /**
   * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
   */
  public com.innowireless.xdb5gcp.rui.TableInfoOrBuilder getTableInfoListOrBuilder(
      int index) {
    return tableInfoList_.get(index);
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
    if (baseResult_ != null) {
      output.writeMessage(1, getBaseResult());
    }
    for (int i = 0; i < tableInfoList_.size(); i++) {
      output.writeMessage(2, tableInfoList_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (baseResult_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getBaseResult());
    }
    for (int i = 0; i < tableInfoList_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, tableInfoList_.get(i));
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
    if (!(obj instanceof com.innowireless.xdb5gcp.rui.TableInfoListResult)) {
      return super.equals(obj);
    }
    com.innowireless.xdb5gcp.rui.TableInfoListResult other = (com.innowireless.xdb5gcp.rui.TableInfoListResult) obj;

    boolean result = true;
    result = result && (hasBaseResult() == other.hasBaseResult());
    if (hasBaseResult()) {
      result = result && getBaseResult()
          .equals(other.getBaseResult());
    }
    result = result && getTableInfoListList()
        .equals(other.getTableInfoListList());
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
    if (hasBaseResult()) {
      hash = (37 * hash) + BASE_RESULT_FIELD_NUMBER;
      hash = (53 * hash) + getBaseResult().hashCode();
    }
    if (getTableInfoListCount() > 0) {
      hash = (37 * hash) + TABLE_INFO_LIST_FIELD_NUMBER;
      hash = (53 * hash) + getTableInfoListList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.innowireless.xdb5gcp.rui.TableInfoListResult parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.innowireless.xdb5gcp.rui.TableInfoListResult parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.innowireless.xdb5gcp.rui.TableInfoListResult parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.innowireless.xdb5gcp.rui.TableInfoListResult parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.innowireless.xdb5gcp.rui.TableInfoListResult parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.innowireless.xdb5gcp.rui.TableInfoListResult parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.innowireless.xdb5gcp.rui.TableInfoListResult parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.innowireless.xdb5gcp.rui.TableInfoListResult parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.innowireless.xdb5gcp.rui.TableInfoListResult parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.innowireless.xdb5gcp.rui.TableInfoListResult parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.innowireless.xdb5gcp.rui.TableInfoListResult parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.innowireless.xdb5gcp.rui.TableInfoListResult parseFrom(
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
  public static Builder newBuilder(com.innowireless.xdb5gcp.rui.TableInfoListResult prototype) {
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
   * Protobuf type {@code xdb_5gcp.rui.TableInfoListResult}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:xdb_5gcp.rui.TableInfoListResult)
      com.innowireless.xdb5gcp.rui.TableInfoListResultOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.innowireless.xdb5gcp.rui.RuiProto.internal_static_xdb_5gcp_rui_TableInfoListResult_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.innowireless.xdb5gcp.rui.RuiProto.internal_static_xdb_5gcp_rui_TableInfoListResult_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.innowireless.xdb5gcp.rui.TableInfoListResult.class, com.innowireless.xdb5gcp.rui.TableInfoListResult.Builder.class);
    }

    // Construct using com.innowireless.xdb5gcp.rui.TableInfoListResult.newBuilder()
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
        getTableInfoListFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (baseResultBuilder_ == null) {
        baseResult_ = null;
      } else {
        baseResult_ = null;
        baseResultBuilder_ = null;
      }
      if (tableInfoListBuilder_ == null) {
        tableInfoList_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        tableInfoListBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.innowireless.xdb5gcp.rui.RuiProto.internal_static_xdb_5gcp_rui_TableInfoListResult_descriptor;
    }

    public com.innowireless.xdb5gcp.rui.TableInfoListResult getDefaultInstanceForType() {
      return com.innowireless.xdb5gcp.rui.TableInfoListResult.getDefaultInstance();
    }

    public com.innowireless.xdb5gcp.rui.TableInfoListResult build() {
      com.innowireless.xdb5gcp.rui.TableInfoListResult result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.innowireless.xdb5gcp.rui.TableInfoListResult buildPartial() {
      com.innowireless.xdb5gcp.rui.TableInfoListResult result = new com.innowireless.xdb5gcp.rui.TableInfoListResult(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (baseResultBuilder_ == null) {
        result.baseResult_ = baseResult_;
      } else {
        result.baseResult_ = baseResultBuilder_.build();
      }
      if (tableInfoListBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          tableInfoList_ = java.util.Collections.unmodifiableList(tableInfoList_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.tableInfoList_ = tableInfoList_;
      } else {
        result.tableInfoList_ = tableInfoListBuilder_.build();
      }
      result.bitField0_ = to_bitField0_;
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
      if (other instanceof com.innowireless.xdb5gcp.rui.TableInfoListResult) {
        return mergeFrom((com.innowireless.xdb5gcp.rui.TableInfoListResult)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.innowireless.xdb5gcp.rui.TableInfoListResult other) {
      if (other == com.innowireless.xdb5gcp.rui.TableInfoListResult.getDefaultInstance()) return this;
      if (other.hasBaseResult()) {
        mergeBaseResult(other.getBaseResult());
      }
      if (tableInfoListBuilder_ == null) {
        if (!other.tableInfoList_.isEmpty()) {
          if (tableInfoList_.isEmpty()) {
            tableInfoList_ = other.tableInfoList_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureTableInfoListIsMutable();
            tableInfoList_.addAll(other.tableInfoList_);
          }
          onChanged();
        }
      } else {
        if (!other.tableInfoList_.isEmpty()) {
          if (tableInfoListBuilder_.isEmpty()) {
            tableInfoListBuilder_.dispose();
            tableInfoListBuilder_ = null;
            tableInfoList_ = other.tableInfoList_;
            bitField0_ = (bitField0_ & ~0x00000002);
            tableInfoListBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getTableInfoListFieldBuilder() : null;
          } else {
            tableInfoListBuilder_.addAllMessages(other.tableInfoList_);
          }
        }
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
      com.innowireless.xdb5gcp.rui.TableInfoListResult parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.innowireless.xdb5gcp.rui.TableInfoListResult) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private com.innowireless.xdb5gcp.rui.BaseResult baseResult_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.innowireless.xdb5gcp.rui.BaseResult, com.innowireless.xdb5gcp.rui.BaseResult.Builder, com.innowireless.xdb5gcp.rui.BaseResultOrBuilder> baseResultBuilder_;
    /**
     * <code>.xdb_5gcp.rui.BaseResult base_result = 1;</code>
     */
    public boolean hasBaseResult() {
      return baseResultBuilder_ != null || baseResult_ != null;
    }
    /**
     * <code>.xdb_5gcp.rui.BaseResult base_result = 1;</code>
     */
    public com.innowireless.xdb5gcp.rui.BaseResult getBaseResult() {
      if (baseResultBuilder_ == null) {
        return baseResult_ == null ? com.innowireless.xdb5gcp.rui.BaseResult.getDefaultInstance() : baseResult_;
      } else {
        return baseResultBuilder_.getMessage();
      }
    }
    /**
     * <code>.xdb_5gcp.rui.BaseResult base_result = 1;</code>
     */
    public Builder setBaseResult(com.innowireless.xdb5gcp.rui.BaseResult value) {
      if (baseResultBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        baseResult_ = value;
        onChanged();
      } else {
        baseResultBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.xdb_5gcp.rui.BaseResult base_result = 1;</code>
     */
    public Builder setBaseResult(
        com.innowireless.xdb5gcp.rui.BaseResult.Builder builderForValue) {
      if (baseResultBuilder_ == null) {
        baseResult_ = builderForValue.build();
        onChanged();
      } else {
        baseResultBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.xdb_5gcp.rui.BaseResult base_result = 1;</code>
     */
    public Builder mergeBaseResult(com.innowireless.xdb5gcp.rui.BaseResult value) {
      if (baseResultBuilder_ == null) {
        if (baseResult_ != null) {
          baseResult_ =
            com.innowireless.xdb5gcp.rui.BaseResult.newBuilder(baseResult_).mergeFrom(value).buildPartial();
        } else {
          baseResult_ = value;
        }
        onChanged();
      } else {
        baseResultBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.xdb_5gcp.rui.BaseResult base_result = 1;</code>
     */
    public Builder clearBaseResult() {
      if (baseResultBuilder_ == null) {
        baseResult_ = null;
        onChanged();
      } else {
        baseResult_ = null;
        baseResultBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.xdb_5gcp.rui.BaseResult base_result = 1;</code>
     */
    public com.innowireless.xdb5gcp.rui.BaseResult.Builder getBaseResultBuilder() {
      
      onChanged();
      return getBaseResultFieldBuilder().getBuilder();
    }
    /**
     * <code>.xdb_5gcp.rui.BaseResult base_result = 1;</code>
     */
    public com.innowireless.xdb5gcp.rui.BaseResultOrBuilder getBaseResultOrBuilder() {
      if (baseResultBuilder_ != null) {
        return baseResultBuilder_.getMessageOrBuilder();
      } else {
        return baseResult_ == null ?
            com.innowireless.xdb5gcp.rui.BaseResult.getDefaultInstance() : baseResult_;
      }
    }
    /**
     * <code>.xdb_5gcp.rui.BaseResult base_result = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.innowireless.xdb5gcp.rui.BaseResult, com.innowireless.xdb5gcp.rui.BaseResult.Builder, com.innowireless.xdb5gcp.rui.BaseResultOrBuilder> 
        getBaseResultFieldBuilder() {
      if (baseResultBuilder_ == null) {
        baseResultBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.innowireless.xdb5gcp.rui.BaseResult, com.innowireless.xdb5gcp.rui.BaseResult.Builder, com.innowireless.xdb5gcp.rui.BaseResultOrBuilder>(
                getBaseResult(),
                getParentForChildren(),
                isClean());
        baseResult_ = null;
      }
      return baseResultBuilder_;
    }

    private java.util.List<com.innowireless.xdb5gcp.rui.TableInfo> tableInfoList_ =
      java.util.Collections.emptyList();
    private void ensureTableInfoListIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        tableInfoList_ = new java.util.ArrayList<com.innowireless.xdb5gcp.rui.TableInfo>(tableInfoList_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.innowireless.xdb5gcp.rui.TableInfo, com.innowireless.xdb5gcp.rui.TableInfo.Builder, com.innowireless.xdb5gcp.rui.TableInfoOrBuilder> tableInfoListBuilder_;

    /**
     * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
     */
    public java.util.List<com.innowireless.xdb5gcp.rui.TableInfo> getTableInfoListList() {
      if (tableInfoListBuilder_ == null) {
        return java.util.Collections.unmodifiableList(tableInfoList_);
      } else {
        return tableInfoListBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
     */
    public int getTableInfoListCount() {
      if (tableInfoListBuilder_ == null) {
        return tableInfoList_.size();
      } else {
        return tableInfoListBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
     */
    public com.innowireless.xdb5gcp.rui.TableInfo getTableInfoList(int index) {
      if (tableInfoListBuilder_ == null) {
        return tableInfoList_.get(index);
      } else {
        return tableInfoListBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
     */
    public Builder setTableInfoList(
        int index, com.innowireless.xdb5gcp.rui.TableInfo value) {
      if (tableInfoListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureTableInfoListIsMutable();
        tableInfoList_.set(index, value);
        onChanged();
      } else {
        tableInfoListBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
     */
    public Builder setTableInfoList(
        int index, com.innowireless.xdb5gcp.rui.TableInfo.Builder builderForValue) {
      if (tableInfoListBuilder_ == null) {
        ensureTableInfoListIsMutable();
        tableInfoList_.set(index, builderForValue.build());
        onChanged();
      } else {
        tableInfoListBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
     */
    public Builder addTableInfoList(com.innowireless.xdb5gcp.rui.TableInfo value) {
      if (tableInfoListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureTableInfoListIsMutable();
        tableInfoList_.add(value);
        onChanged();
      } else {
        tableInfoListBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
     */
    public Builder addTableInfoList(
        int index, com.innowireless.xdb5gcp.rui.TableInfo value) {
      if (tableInfoListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureTableInfoListIsMutable();
        tableInfoList_.add(index, value);
        onChanged();
      } else {
        tableInfoListBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
     */
    public Builder addTableInfoList(
        com.innowireless.xdb5gcp.rui.TableInfo.Builder builderForValue) {
      if (tableInfoListBuilder_ == null) {
        ensureTableInfoListIsMutable();
        tableInfoList_.add(builderForValue.build());
        onChanged();
      } else {
        tableInfoListBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
     */
    public Builder addTableInfoList(
        int index, com.innowireless.xdb5gcp.rui.TableInfo.Builder builderForValue) {
      if (tableInfoListBuilder_ == null) {
        ensureTableInfoListIsMutable();
        tableInfoList_.add(index, builderForValue.build());
        onChanged();
      } else {
        tableInfoListBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
     */
    public Builder addAllTableInfoList(
        java.lang.Iterable<? extends com.innowireless.xdb5gcp.rui.TableInfo> values) {
      if (tableInfoListBuilder_ == null) {
        ensureTableInfoListIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, tableInfoList_);
        onChanged();
      } else {
        tableInfoListBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
     */
    public Builder clearTableInfoList() {
      if (tableInfoListBuilder_ == null) {
        tableInfoList_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        tableInfoListBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
     */
    public Builder removeTableInfoList(int index) {
      if (tableInfoListBuilder_ == null) {
        ensureTableInfoListIsMutable();
        tableInfoList_.remove(index);
        onChanged();
      } else {
        tableInfoListBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
     */
    public com.innowireless.xdb5gcp.rui.TableInfo.Builder getTableInfoListBuilder(
        int index) {
      return getTableInfoListFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
     */
    public com.innowireless.xdb5gcp.rui.TableInfoOrBuilder getTableInfoListOrBuilder(
        int index) {
      if (tableInfoListBuilder_ == null) {
        return tableInfoList_.get(index);  } else {
        return tableInfoListBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
     */
    public java.util.List<? extends com.innowireless.xdb5gcp.rui.TableInfoOrBuilder> 
         getTableInfoListOrBuilderList() {
      if (tableInfoListBuilder_ != null) {
        return tableInfoListBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(tableInfoList_);
      }
    }
    /**
     * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
     */
    public com.innowireless.xdb5gcp.rui.TableInfo.Builder addTableInfoListBuilder() {
      return getTableInfoListFieldBuilder().addBuilder(
          com.innowireless.xdb5gcp.rui.TableInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
     */
    public com.innowireless.xdb5gcp.rui.TableInfo.Builder addTableInfoListBuilder(
        int index) {
      return getTableInfoListFieldBuilder().addBuilder(
          index, com.innowireless.xdb5gcp.rui.TableInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .xdb_5gcp.rui.TableInfo table_info_list = 2;</code>
     */
    public java.util.List<com.innowireless.xdb5gcp.rui.TableInfo.Builder> 
         getTableInfoListBuilderList() {
      return getTableInfoListFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.innowireless.xdb5gcp.rui.TableInfo, com.innowireless.xdb5gcp.rui.TableInfo.Builder, com.innowireless.xdb5gcp.rui.TableInfoOrBuilder> 
        getTableInfoListFieldBuilder() {
      if (tableInfoListBuilder_ == null) {
        tableInfoListBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.innowireless.xdb5gcp.rui.TableInfo, com.innowireless.xdb5gcp.rui.TableInfo.Builder, com.innowireless.xdb5gcp.rui.TableInfoOrBuilder>(
                tableInfoList_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        tableInfoList_ = null;
      }
      return tableInfoListBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:xdb_5gcp.rui.TableInfoListResult)
  }

  // @@protoc_insertion_point(class_scope:xdb_5gcp.rui.TableInfoListResult)
  private static final com.innowireless.xdb5gcp.rui.TableInfoListResult DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.innowireless.xdb5gcp.rui.TableInfoListResult();
  }

  public static com.innowireless.xdb5gcp.rui.TableInfoListResult getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TableInfoListResult>
      PARSER = new com.google.protobuf.AbstractParser<TableInfoListResult>() {
    public TableInfoListResult parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new TableInfoListResult(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TableInfoListResult> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TableInfoListResult> getParserForType() {
    return PARSER;
  }

  public com.innowireless.xdb5gcp.rui.TableInfoListResult getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

