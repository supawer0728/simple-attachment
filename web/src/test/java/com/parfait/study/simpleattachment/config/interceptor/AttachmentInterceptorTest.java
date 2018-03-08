package com.parfait.study.simpleattachment.config.interceptor;

import com.parfait.study.simpleattachment.attachment.Attach;
import com.parfait.study.simpleattachment.attachment.AttachmentTypeHolder;
import com.parfait.study.simpleattachment.shared.model.attachment.AttachmentType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

public class AttachmentInterceptorTest {

    @InjectMocks
    private AttachmentInterceptor attachmentInterceptor;
    @Spy
    private AttachmentTypeHolder attachmentTypeHolder;
    @Mock
    private HandlerMethod handlerMethod;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void preHandle() throws Exception {
        // given
        given(handlerMethod.hasMethodAnnotation(Attach.class)).willReturn(true);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter(AttachmentInterceptor.TARGET_PARAMETER_NAME, AttachmentType.COMMENTS.name().toLowerCase());
        MockHttpServletResponse response = new MockHttpServletResponse();

        // when
        attachmentInterceptor.preHandle(request, response, handlerMethod);

        // then
        assertThat(attachmentTypeHolder.getTypes(), hasItem(AttachmentType.COMMENTS));
    }
}
