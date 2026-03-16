package com.programmingtechie.product_service;


class ProductServiceApplicationTests {

	/*
	/// Criou -se um mongo db container for tests only, tens que mandar a versao do mongo DB que vais usar
	@Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper; // ← para converter object → JSON


	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}



	@Test
	void shouldCreateProduct() throws Exception {

		ProductRequest productRequest = getProductRequest();

		mockMvc.perform(post("/api/product/add")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(productRequest))
				).andExpect(status().isCreated());
	}

	@Test
	void retornaProduto() throws Exception {

		List<ProductResponse> productResponses;
		String response = mockMvc.perform(get("/api/product/getAll"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn()
				.getResponse()
				.getContentAsString();

		System.out.println(response);

	}


	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("Iphone 13")
				.description("Iphone 13")
				.price(BigDecimal.valueOf(1200))
				.build();
	}

	 */


}
