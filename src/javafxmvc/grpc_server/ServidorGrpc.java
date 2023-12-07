package src.javafxmvc.grpc_server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import src.javafxmvc.grpc_server.Mensagem.*;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServidorGrpc {

    private Server server;

    private void start() throws IOException {
        int port = 12345;
        server = ServerBuilder.forPort(port)
                .addService(new EquipeServiceImpl())
                .build()
                .start();

        System.out.println("Servidor iniciado na porta " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Desligando o servidor...");
            ServidorGrpc.this.stop();
            System.out.println("Servidor desligado.");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ServidorGrpc servidor = new ServidorGrpc();
        servidor.start();
        servidor.blockUntilShutdown();
    }

    private static class EquipeServiceImpl extends EquipeServiceGrpc.EquipeServiceImplBase {
        @Override
        public void obterEquipeInfo(InfoEquipeRequest request, StreamObserver<InfoEquipeResponse> responseObserver) {
            // Implemente a lógica para obter informações da equipe
            int id = request.getId();
            List<String> equipe = obterMembrosEquipe(id);
            InfoEquipeResponse response = InfoEquipeResponse.newBuilder().addAllMembros(equipe).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void verificarCupom(VerificarCupomRequest request, StreamObserver<VerificarCupomResponse> responseObserver) {
            // Implemente a lógica para verificar o cupom
            String cupom = request.getCupom();
            boolean valido = verificarCupomValido(cupom);
            VerificarCupomResponse response = VerificarCupomResponse.newBuilder().setValido(valido).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        private static List<String> obterMembrosEquipe(int id) {
        List<String> eq = new ArrayList<>();
        switch (id) {
            case 1:
                eq.add("Magno Leal De Brito Junior");
                eq.add("Renan Gomes Poggian");
                eq.add("Vitor Miller de Toledo");
                break;
            case 2:
                eq.add("Milena Melo Linhares");
                eq.add("Mirielle Rosa de Souza");
                eq.add("Yuri Fabres de Jesus Figueira");
                break;
            case 3:
                eq.add("Gabriely Machado Carrari");
                eq.add("Alda Maria Norbiato Torres");
                eq.add("Fernanda De Oliveira Ramos");
                break;
            case 4:
                eq.add("Marcelo Alves Santos");
                eq.add("Livia Guimarães De Jesus");
                eq.add("Gabriel Cardoso Girarde");
                break;
            case 5:
                eq.add("Matheus da Silva Módolo");
                eq.add("Sofia Pereira Bachetti Sartorio");
                eq.add("Larissa Paganini");
                break;
            case 6:
                eq.add("Silvio Lopes Ribeiro");
                eq.add("Murillo Carlete Ribeiro");
                eq.add("Róger Corrente Pinto");
                break;
            case 7:
                eq.add("Douglas Altoé Stein da Silva");
                eq.add("Diego Chaves Ravani");
                eq.add("Carlos Eduardo de Menezes Pacheco");
                break;
            case 8:
                eq.add("Eduardo Souto Siqueira Santana");
                eq.add("Rafael Savignon de Resende");
                eq.add("Felipe Moreira da Paz");
                break;
            case 9:
                eq.add("Wesley Pereira da Silva");
                eq.add("João Victor de Salles Mapeli Couzzi");
                eq.add("João Victor Maciel Campos");
                break;
            case 10:
                eq.add("Igor Almeida da Silva");
                eq.add("Raphael Pavani Manhães Bersot");
                eq.add("Júlia Borges Santos");
                break;
            default:
                eq.add("Número da equipe informado está incorreto!");
        }
        return eq;
    }

    private static boolean verificarCupomValido(String cupom) {
        final String[] cuponsValidos = {
                "AB12CD",
                "EF34GH",
                "IJ56KL",
                "MN78OP",
                "QR90ST",
                "UV12WX",
                "YZ34AB",
                "CD56EF",
                "GH78IJ",
                "KL90MN" };
        
        if(Arrays.asList(cuponsValidos).contains(cupom.toUpperCase())) return true;
            
        return false;
    }
    }
}
