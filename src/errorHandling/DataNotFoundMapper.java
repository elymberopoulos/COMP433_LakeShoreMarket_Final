package errorHandling;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataNotFoundMapper implements ExceptionMapper<DataNotFound>{
	@Override
	public Response toResponse(DataNotFound ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404, "http://lakeshoremarket.com/support");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
		
	}


}
