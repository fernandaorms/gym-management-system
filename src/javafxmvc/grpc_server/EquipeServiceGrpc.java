package src.javafxmvc.grpc_server;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(value = "by gRPC proto compiler (version 1.59.1)", comments = "Source: mensagem.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class EquipeServiceGrpc {

  private EquipeServiceGrpc() {
  }

  public static final java.lang.String SERVICE_NAME = "EquipeService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<Mensagem.InfoEquipeRequest, Mensagem.InfoEquipeResponse> getObterEquipeInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
      + "ObterEquipeInfo", requestType = Mensagem.InfoEquipeRequest.class, responseType = Mensagem.InfoEquipeResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Mensagem.InfoEquipeRequest, Mensagem.InfoEquipeResponse> getObterEquipeInfoMethod() {
    io.grpc.MethodDescriptor<Mensagem.InfoEquipeRequest, Mensagem.InfoEquipeResponse> getObterEquipeInfoMethod;
    if ((getObterEquipeInfoMethod = EquipeServiceGrpc.getObterEquipeInfoMethod) == null) {
      synchronized (EquipeServiceGrpc.class) {
        if ((getObterEquipeInfoMethod = EquipeServiceGrpc.getObterEquipeInfoMethod) == null) {
          EquipeServiceGrpc.getObterEquipeInfoMethod = getObterEquipeInfoMethod = io.grpc.MethodDescriptor.<Mensagem.InfoEquipeRequest, Mensagem.InfoEquipeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ObterEquipeInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Mensagem.InfoEquipeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Mensagem.InfoEquipeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EquipeServiceMethodDescriptorSupplier("ObterEquipeInfo"))
              .build();
        }
      }
    }
    return getObterEquipeInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<Mensagem.VerificarCupomRequest, Mensagem.VerificarCupomResponse> getVerificarCupomMethod;

  @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
      + "VerificarCupom", requestType = Mensagem.VerificarCupomRequest.class, responseType = Mensagem.VerificarCupomResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Mensagem.VerificarCupomRequest, Mensagem.VerificarCupomResponse> getVerificarCupomMethod() {
    io.grpc.MethodDescriptor<Mensagem.VerificarCupomRequest, Mensagem.VerificarCupomResponse> getVerificarCupomMethod;
    if ((getVerificarCupomMethod = EquipeServiceGrpc.getVerificarCupomMethod) == null) {
      synchronized (EquipeServiceGrpc.class) {
        if ((getVerificarCupomMethod = EquipeServiceGrpc.getVerificarCupomMethod) == null) {
          EquipeServiceGrpc.getVerificarCupomMethod = getVerificarCupomMethod = io.grpc.MethodDescriptor.<Mensagem.VerificarCupomRequest, Mensagem.VerificarCupomResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "VerificarCupom"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Mensagem.VerificarCupomRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Mensagem.VerificarCupomResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EquipeServiceMethodDescriptorSupplier("VerificarCupom"))
              .build();
        }
      }
    }
    return getVerificarCupomMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EquipeServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EquipeServiceStub> factory = new io.grpc.stub.AbstractStub.StubFactory<EquipeServiceStub>() {
      @java.lang.Override
      public EquipeServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new EquipeServiceStub(channel, callOptions);
      }
    };
    return EquipeServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output
   * calls on the service
   */
  public static EquipeServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EquipeServiceBlockingStub> factory = new io.grpc.stub.AbstractStub.StubFactory<EquipeServiceBlockingStub>() {
      @java.lang.Override
      public EquipeServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new EquipeServiceBlockingStub(channel, callOptions);
      }
    };
    return EquipeServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the
   * service
   */
  public static EquipeServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EquipeServiceFutureStub> factory = new io.grpc.stub.AbstractStub.StubFactory<EquipeServiceFutureStub>() {
      @java.lang.Override
      public EquipeServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
        return new EquipeServiceFutureStub(channel, callOptions);
      }
    };
    return EquipeServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void obterEquipeInfo(Mensagem.InfoEquipeRequest request,
        io.grpc.stub.StreamObserver<Mensagem.InfoEquipeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getObterEquipeInfoMethod(), responseObserver);
    }

    /**
     */
    default void verificarCupom(Mensagem.VerificarCupomRequest request,
        io.grpc.stub.StreamObserver<Mensagem.VerificarCupomResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getVerificarCupomMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service EquipeService.
   */
  public static abstract class EquipeServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override
    public final io.grpc.ServerServiceDefinition bindService() {
      return EquipeServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service
   * EquipeService.
   */
  public static final class EquipeServiceStub
      extends io.grpc.stub.AbstractAsyncStub<EquipeServiceStub> {
    private EquipeServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EquipeServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EquipeServiceStub(channel, callOptions);
    }

    /**
     */
    public void obterEquipeInfo(Mensagem.InfoEquipeRequest request,
        io.grpc.stub.StreamObserver<Mensagem.InfoEquipeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getObterEquipeInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void verificarCupom(Mensagem.VerificarCupomRequest request,
        io.grpc.stub.StreamObserver<Mensagem.VerificarCupomResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getVerificarCupomMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service EquipeService.
   */
  public static final class EquipeServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<EquipeServiceBlockingStub> {
    private EquipeServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EquipeServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EquipeServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public Mensagem.InfoEquipeResponse obterEquipeInfo(Mensagem.InfoEquipeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getObterEquipeInfoMethod(), getCallOptions(), request);
    }

    /**
     */
    public Mensagem.VerificarCupomResponse verificarCupom(Mensagem.VerificarCupomRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getVerificarCupomMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service
   * EquipeService.
   */
  public static final class EquipeServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<EquipeServiceFutureStub> {
    private EquipeServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EquipeServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EquipeServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Mensagem.InfoEquipeResponse> obterEquipeInfo(
        Mensagem.InfoEquipeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getObterEquipeInfoMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Mensagem.VerificarCupomResponse> verificarCupom(
        Mensagem.VerificarCupomRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getVerificarCupomMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_OBTER_EQUIPE_INFO = 0;
  private static final int METHODID_VERIFICAR_CUPOM = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_OBTER_EQUIPE_INFO:
          serviceImpl.obterEquipeInfo((Mensagem.InfoEquipeRequest) request,
              (io.grpc.stub.StreamObserver<Mensagem.InfoEquipeResponse>) responseObserver);
          break;
        case METHODID_VERIFICAR_CUPOM:
          serviceImpl.verificarCupom((Mensagem.VerificarCupomRequest) request,
              (io.grpc.stub.StreamObserver<Mensagem.VerificarCupomResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
            getObterEquipeInfoMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
                new MethodHandlers<Mensagem.InfoEquipeRequest, Mensagem.InfoEquipeResponse>(
                    service, METHODID_OBTER_EQUIPE_INFO)))
        .addMethod(
            getVerificarCupomMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
                new MethodHandlers<Mensagem.VerificarCupomRequest, Mensagem.VerificarCupomResponse>(
                    service, METHODID_VERIFICAR_CUPOM)))
        .build();
  }

  private static abstract class EquipeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EquipeServiceBaseDescriptorSupplier() {
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Mensagem.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EquipeService");
    }
  }

  private static final class EquipeServiceFileDescriptorSupplier
      extends EquipeServiceBaseDescriptorSupplier {
    EquipeServiceFileDescriptorSupplier() {
    }
  }

  private static final class EquipeServiceMethodDescriptorSupplier
      extends EquipeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    EquipeServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (EquipeServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EquipeServiceFileDescriptorSupplier())
              .addMethod(getObterEquipeInfoMethod())
              .addMethod(getVerificarCupomMethod())
              .build();
        }
      }
    }
    return result;
  }
}
