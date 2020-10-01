package br.ucsal.controller;

public class ControllerTest {

   /* @Mock
    private HistoricoTrocas historicoTrocas;

    private LivroTrocaDAO livroTrocaDAO;
    static private HttpServletRequest request;
    static private HttpServletResponse response;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }


    @Test
    public void HistoricoLoginTest() throws ServletException, IOException {
        ServletRunner sr = new ServletRunner();
        sr.registerServlet("TabelaCampeonatoServlet", TabelaCampeonatoServlet.class.getName());
        ServletUnitClient sc = sr.newClient();
        WebRequest request = new PostMethodWebRequest("http://localhost/TabelaCampeonatoServlet");

        try {
            InvocationContext ic = sc.newInvocation(request);
            TabelaCampeonatoServlet tabelaServlet = (TabelaCampeonatoServlet) ic.getServlet();
            Collection times = tabelaServlet.getTimes(ic.getRequest());
            assertTrue(times.contains("Flamengo"));
            assertTrue(times.contains("Vasco"));
            assertEquals(times.size(), 2);
        } catch (Exception e) {
            fail("Error testing TabelaCampeonatoServlet Exception is " + e);
            e.printStackTrace();
        }
    }


    @Test
    public void HistoricoTrocasTest() throws ServletException, IOException {

        Mockito.when(this.historicoTrocas.doGet(request, response)).thenReturn();


        Mockito.verify(this.historicoTrocas, times(1)).doGet(request, response);
        Mockito.verify(this.livroTrocaDAO, times(1)).buscarHistorico(1);

        Mockito.when(this.materiaRepository.findById(1L)).thenReturn(Optional.of(materiaEntity));
        //Quando o metodo retorna void
        Mockito.doThrow(IllegalStateException.class).when(this.materiaRepository).deleteById(1l);

        MateriaException materiaException;


        materiaException = assertThrows(MateriaException.class, () -> {
            this.materiaService.excluir(1l);
        });

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, materiaException.getHttpStatus());
        assertEquals(MensagensConstant.ERRO_GENERICO.getValor(), materiaException.getMessage());


    }
*/
}
