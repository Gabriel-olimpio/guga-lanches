public class PedidoResumo {


    public static String formatar(Pedido pedido) {
        StringBuilder resumo = new StringBuilder("===== RESUMO DO PEDIDO =====\n\n");
        resumo.append("Cliente: ").append(pedido.getCliente().getNome()).append("\n");
        resumo.append("Telefone: ").append(pedido.getCliente().getTelefone()).append("\n\n");
        resumo.append("Itens:\n");
        for (ItemPedido item : pedido.getItens()) {
            resumo.append(String.format("- %s (%d un) - R$ %.2f\n",
                    item.getProduto().getNome(), item.getQuantidade(), item.getSubtotal()));
        }
        resumo.append("\n----------------------------------\n");
        resumo.append(String.format("TOTAL: R$ %.2f\n", pedido.getValorTotal()));
        resumo.append("Forma de Pagamento: ").append(pedido.getFormaPagamento()).append("\n");
        resumo.append("Consumo: ").append(pedido.getTipoConsumo()).append("\n");
        return resumo.toString();
    }
}
