// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: xdb_5gcp_rui.proto

package com.innowireless.xdb5gcp.rui;

public final class RuiProto {
  private RuiProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_xdb_5gcp_rui_BaseResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_xdb_5gcp_rui_BaseResult_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_xdb_5gcp_rui_BaseResult_SyntaxErrorPos_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_xdb_5gcp_rui_BaseResult_SyntaxErrorPos_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_xdb_5gcp_rui_Key_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_xdb_5gcp_rui_Key_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_xdb_5gcp_rui_QueryRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_xdb_5gcp_rui_QueryRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_xdb_5gcp_rui_GenerateResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_xdb_5gcp_rui_GenerateResult_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_xdb_5gcp_rui_ExecutionResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_xdb_5gcp_rui_ExecutionResult_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_xdb_5gcp_rui_Empty_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_xdb_5gcp_rui_Empty_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_xdb_5gcp_rui_DatasetRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_xdb_5gcp_rui_DatasetRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_xdb_5gcp_rui_Dataset_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_xdb_5gcp_rui_Dataset_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_xdb_5gcp_rui_TableInfoListRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_xdb_5gcp_rui_TableInfoListRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_xdb_5gcp_rui_TableInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_xdb_5gcp_rui_TableInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_xdb_5gcp_rui_TableInfoListResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_xdb_5gcp_rui_TableInfoListResult_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_xdb_5gcp_rui_TableName_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_xdb_5gcp_rui_TableName_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_xdb_5gcp_rui_TableSchemaResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_xdb_5gcp_rui_TableSchemaResult_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_xdb_5gcp_rui_TableSchemaResult_ColumnInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_xdb_5gcp_rui_TableSchemaResult_ColumnInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_xdb_5gcp_rui_TableSchemaResult_TimePartition_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_xdb_5gcp_rui_TableSchemaResult_TimePartition_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_xdb_5gcp_rui_RemoveTimePartitionRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_xdb_5gcp_rui_RemoveTimePartitionRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022xdb_5gcp_rui.proto\022\014xdb_5gcp.rui\"\375\002\n\nB" +
      "aseResult\0228\n\013result_code\030\001 \001(\0162#.xdb_5gc" +
      "p.rui.BaseResult.ResultCode\022\025\n\rerror_mes" +
      "sage\030\002 \001(\t\022A\n\020syntax_error_pos\030\003 \001(\0132\'.x" +
      "db_5gcp.rui.BaseResult.SyntaxErrorPos\032s\n" +
      "\016SyntaxErrorPos\022\027\n\017error_pos_exist\030\001 \001(\010" +
      "\022\022\n\nstart_line\030\002 \001(\005\022\020\n\010end_line\030\003 \001(\005\022\021" +
      "\n\tstart_pos\030\004 \001(\005\022\017\n\007end_pos\030\005 \001(\005\"f\n\nRe" +
      "sultCode\022\r\n\tRESULT_OK\020\000\022\020\n\014RESULT_ERROR\020" +
      "\001\022\030\n\024RESULT_KEY_NOT_FOUND\020\002\022\035\n\031RESULT_IN" +
      "PUT_SYNTAX_ERROR\020\003\"\031\n\003Key\022\022\n\nkey_string\030" +
      "\001 \001(\t\"i\n\014QueryRequest\022\025\n\ruser_identity\030\001" +
      " \001(\t\022\022\n\nsql_string\030\002 \001(\t\022\027\n\017start_timest" +
      "amp\030\003 \001(\003\022\025\n\rend_timestamp\030\004 \001(\003\"\210\001\n\016Gen" +
      "erateResult\022-\n\013base_result\030\001 \001(\0132\030.xdb_5" +
      "gcp.rui.BaseResult\022\036\n\003key\030\002 \001(\0132\021.xdb_5g" +
      "cp.rui.Key\022\'\n\010sql_type\030\003 \001(\0162\025.xdb_5gcp." +
      "rui.SqlType\"\367\003\n\017ExecutionResult\022-\n\013base_" +
      "result\030\001 \001(\0132\030.xdb_5gcp.rui.BaseResult\022\'" +
      "\n\010sql_type\030\002 \001(\0162\025.xdb_5gcp.rui.SqlType\022" +
      "G\n\020execution_status\030\003 \001(\0162-.xdb_5gcp.rui" +
      ".ExecutionResult.ExecutionStatus\022N\n\024exec" +
      "ution_error_code\030\004 \001(\01620.xdb_5gcp.rui.Ex" +
      "ecutionResult.ExecutionErrorCode\022\031\n\021exec" +
      "ution_message\030\005 \001(\t\022\026\n\016execution_time\030\006 " +
      "\001(\003\022\030\n\020result_row_count\030\007 \001(\003\"n\n\017Executi" +
      "onStatus\022\022\n\016STATUS_UNKNOWN\020\000\022\020\n\014STATUS_R" +
      "EADY\020\001\022\022\n\016STATUS_RUNNING\020\002\022\017\n\013STATUS_DON" +
      "E\020\003\022\020\n\014STATUS_ERROR\020\004\"6\n\022ExecutionErrorC" +
      "ode\022\r\n\tNOT_ERROR\020\000\022\021\n\rUNKNOWN_ERROR\020\001\"\007\n" +
      "\005Empty\"Q\n\016DatasetRequest\022\022\n\ntable_name\030\001" +
      " \001(\t\022\025\n\rrecord_offset\030\002 \001(\003\022\024\n\014record_co" +
      "unt\030\003 \001(\003\"\031\n\007Dataset\022\016\n\006record\030\001 \003(\t\"\321\001\n" +
      "\024TableInfoListRequest\022G\n\ntable_type\030\001 \001(" +
      "\01623.xdb_5gcp.rui.TableInfoListRequest.Re" +
      "questTableType\022\030\n\020table_name_regex\030\002 \001(\t" +
      "\"V\n\020RequestTableType\022\r\n\tALL_TABLE\020\000\022\021\n\rI" +
      "SA_HUB_TABLE\020\001\022\016\n\nUSER_TABLE\020\002\022\020\n\014MEMORY" +
      "_TABLE\020\003\"\325\001\n\tTableInfo\0225\n\ntable_type\030\001 \001" +
      "(\0162!.xdb_5gcp.rui.TableInfo.TableType\022\022\n" +
      "\ntable_name\030\002 \001(\t\022\025\n\ruser_identity\030\003 \001(\t" +
      "\022\027\n\017total_row_count\030\004 \001(\003\"M\n\tTableType\022\013" +
      "\n\007UNKNOWN\020\000\022\021\n\rISA_HUB_TABLE\020\001\022\016\n\nUSER_T" +
      "ABLE\020\002\022\020\n\014MEMORY_TABLE\020\003\"v\n\023TableInfoLis" +
      "tResult\022-\n\013base_result\030\001 \001(\0132\030.xdb_5gcp." +
      "rui.BaseResult\0220\n\017table_info_list\030\002 \003(\0132" +
      "\027.xdb_5gcp.rui.TableInfo\"\037\n\tTableName\022\022\n" +
      "\ntable_name\030\001 \001(\t\"\311\004\n\021TableSchemaResult\022" +
      "-\n\013base_result\030\001 \001(\0132\030.xdb_5gcp.rui.Base" +
      "Result\022+\n\ntable_info\030\002 \001(\0132\027.xdb_5gcp.ru" +
      "i.TableInfo\022D\n\020column_info_list\030\003 \003(\0132*." +
      "xdb_5gcp.rui.TableSchemaResult.ColumnInf" +
      "o\022J\n\023time_partition_list\030\004 \003(\0132-.xdb_5gc" +
      "p.rui.TableSchemaResult.TimePartition\032\205\002" +
      "\n\nColumnInfo\022\023\n\013column_name\030\001 \001(\t\022H\n\013col" +
      "umn_type\030\002 \001(\01623.xdb_5gcp.rui.TableSchem" +
      "aResult.ColumnInfo.DataType\022\025\n\rcolumn_le" +
      "ngth\030\003 \001(\005\022\020\n\010nullable\030\004 \001(\010\"o\n\010DataType" +
      "\022\r\n\tTYPE_NULL\020\000\022\016\n\nTYPE_INT32\020\001\022\016\n\nTYPE_" +
      "INT64\020\002\022\017\n\013TYPE_DOUBLE\020\003\022\017\n\013TYPE_STRING\020" +
      "\004\022\022\n\016TYPE_TIMESTAMP\020\005\032>\n\rTimePartition\022\032" +
      "\n\022partition_datetime\030\001 \001(\t\022\021\n\trow_count\030" +
      "\002 \001(\003\"^\n\032RemoveTimePartitionRequest\022\022\n\nt" +
      "able_name\030\001 \001(\t\022\026\n\016start_datetime\030\002 \001(\t\022" +
      "\024\n\014end_datetime\030\003 \001(\t*m\n\007SqlType\022\017\n\013SELE" +
      "CT_STMT\020\000\022\017\n\013CREATE_STMT\020\001\022\r\n\tDROP_STMT\020" +
      "\002\022\017\n\013INSERT_STMT\020\003\022\017\n\013IMPORT_STMT\020\004\022\017\n\013E" +
      "XPORT_STMT\020\0052\261\005\n\nXdbService\022L\n\020GenerateQ" +
      "ueryKey\022\032.xdb_5gcp.rui.QueryRequest\032\034.xd" +
      "b_5gcp.rui.GenerateResult\022E\n\017ExecuteQuer" +
      "yKey\022\021.xdb_5gcp.rui.Key\032\035.xdb_5gcp.rui.E" +
      "xecutionResult0\001\022D\n\020GetExecutionInfo\022\021.x" +
      "db_5gcp.rui.Key\032\035.xdb_5gcp.rui.Execution" +
      "Result\022=\n\016RemoveQueryKey\022\021.xdb_5gcp.rui." +
      "Key\032\030.xdb_5gcp.rui.BaseResult\022B\n\021RemoveQ" +
      "ueryKeyAll\022\023.xdb_5gcp.rui.Empty\032\030.xdb_5g" +
      "cp.rui.BaseResult\022C\n\nGetDataset\022\034.xdb_5g" +
      "cp.rui.DatasetRequest\032\025.xdb_5gcp.rui.Dat" +
      "aset0\001\022Y\n\020GetTableInfoList\022\".xdb_5gcp.ru" +
      "i.TableInfoListRequest\032!.xdb_5gcp.rui.Ta" +
      "bleInfoListResult\022J\n\016GetTableSchema\022\027.xd" +
      "b_5gcp.rui.TableName\032\037.xdb_5gcp.rui.Tabl" +
      "eSchemaResult\022Y\n\023RemoveTimePartition\022(.x" +
      "db_5gcp.rui.RemoveTimePartitionRequest\032\030" +
      ".xdb_5gcp.rui.BaseResultB,\n\034com.innowire" +
      "less.xdb5gcp.ruiB\010RuiProtoH\001P\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_xdb_5gcp_rui_BaseResult_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_xdb_5gcp_rui_BaseResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_xdb_5gcp_rui_BaseResult_descriptor,
        new java.lang.String[] { "ResultCode", "ErrorMessage", "SyntaxErrorPos", });
    internal_static_xdb_5gcp_rui_BaseResult_SyntaxErrorPos_descriptor =
      internal_static_xdb_5gcp_rui_BaseResult_descriptor.getNestedTypes().get(0);
    internal_static_xdb_5gcp_rui_BaseResult_SyntaxErrorPos_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_xdb_5gcp_rui_BaseResult_SyntaxErrorPos_descriptor,
        new java.lang.String[] { "ErrorPosExist", "StartLine", "EndLine", "StartPos", "EndPos", });
    internal_static_xdb_5gcp_rui_Key_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_xdb_5gcp_rui_Key_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_xdb_5gcp_rui_Key_descriptor,
        new java.lang.String[] { "KeyString", });
    internal_static_xdb_5gcp_rui_QueryRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_xdb_5gcp_rui_QueryRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_xdb_5gcp_rui_QueryRequest_descriptor,
        new java.lang.String[] { "UserIdentity", "SqlString", "StartTimestamp", "EndTimestamp", });
    internal_static_xdb_5gcp_rui_GenerateResult_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_xdb_5gcp_rui_GenerateResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_xdb_5gcp_rui_GenerateResult_descriptor,
        new java.lang.String[] { "BaseResult", "Key", "SqlType", });
    internal_static_xdb_5gcp_rui_ExecutionResult_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_xdb_5gcp_rui_ExecutionResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_xdb_5gcp_rui_ExecutionResult_descriptor,
        new java.lang.String[] { "BaseResult", "SqlType", "ExecutionStatus", "ExecutionErrorCode", "ExecutionMessage", "ExecutionTime", "ResultRowCount", });
    internal_static_xdb_5gcp_rui_Empty_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_xdb_5gcp_rui_Empty_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_xdb_5gcp_rui_Empty_descriptor,
        new java.lang.String[] { });
    internal_static_xdb_5gcp_rui_DatasetRequest_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_xdb_5gcp_rui_DatasetRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_xdb_5gcp_rui_DatasetRequest_descriptor,
        new java.lang.String[] { "TableName", "RecordOffset", "RecordCount", });
    internal_static_xdb_5gcp_rui_Dataset_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_xdb_5gcp_rui_Dataset_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_xdb_5gcp_rui_Dataset_descriptor,
        new java.lang.String[] { "Record", });
    internal_static_xdb_5gcp_rui_TableInfoListRequest_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_xdb_5gcp_rui_TableInfoListRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_xdb_5gcp_rui_TableInfoListRequest_descriptor,
        new java.lang.String[] { "TableType", "TableNameRegex", });
    internal_static_xdb_5gcp_rui_TableInfo_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_xdb_5gcp_rui_TableInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_xdb_5gcp_rui_TableInfo_descriptor,
        new java.lang.String[] { "TableType", "TableName", "UserIdentity", "TotalRowCount", });
    internal_static_xdb_5gcp_rui_TableInfoListResult_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_xdb_5gcp_rui_TableInfoListResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_xdb_5gcp_rui_TableInfoListResult_descriptor,
        new java.lang.String[] { "BaseResult", "TableInfoList", });
    internal_static_xdb_5gcp_rui_TableName_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_xdb_5gcp_rui_TableName_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_xdb_5gcp_rui_TableName_descriptor,
        new java.lang.String[] { "TableName", });
    internal_static_xdb_5gcp_rui_TableSchemaResult_descriptor =
      getDescriptor().getMessageTypes().get(12);
    internal_static_xdb_5gcp_rui_TableSchemaResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_xdb_5gcp_rui_TableSchemaResult_descriptor,
        new java.lang.String[] { "BaseResult", "TableInfo", "ColumnInfoList", "TimePartitionList", });
    internal_static_xdb_5gcp_rui_TableSchemaResult_ColumnInfo_descriptor =
      internal_static_xdb_5gcp_rui_TableSchemaResult_descriptor.getNestedTypes().get(0);
    internal_static_xdb_5gcp_rui_TableSchemaResult_ColumnInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_xdb_5gcp_rui_TableSchemaResult_ColumnInfo_descriptor,
        new java.lang.String[] { "ColumnName", "ColumnType", "ColumnLength", "Nullable", });
    internal_static_xdb_5gcp_rui_TableSchemaResult_TimePartition_descriptor =
      internal_static_xdb_5gcp_rui_TableSchemaResult_descriptor.getNestedTypes().get(1);
    internal_static_xdb_5gcp_rui_TableSchemaResult_TimePartition_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_xdb_5gcp_rui_TableSchemaResult_TimePartition_descriptor,
        new java.lang.String[] { "PartitionDatetime", "RowCount", });
    internal_static_xdb_5gcp_rui_RemoveTimePartitionRequest_descriptor =
      getDescriptor().getMessageTypes().get(13);
    internal_static_xdb_5gcp_rui_RemoveTimePartitionRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_xdb_5gcp_rui_RemoveTimePartitionRequest_descriptor,
        new java.lang.String[] { "TableName", "StartDatetime", "EndDatetime", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}