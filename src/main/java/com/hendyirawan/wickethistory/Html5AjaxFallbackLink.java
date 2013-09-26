package com.hendyirawan.wickethistory;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.IAjaxLink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.stateless.StatelessAjaxEventBehavior;
import org.wicketstuff.stateless.StatelessLink;

/**
 * Just like {@link AjaxFallbackLink}, but stateless.
 * 
 * @author jfk
 */
public abstract class Html5AjaxFallbackLink<T> extends StatelessLink<T>
        implements IAjaxLink
{

    private static final long serialVersionUID = -133600842398684777L;

    public Html5AjaxFallbackLink(final String id)
    {
        this(id, null, null);
    }

	public Html5AjaxFallbackLink(final String id, final PageParameters params)
	{
		this(id, null, params);
	}

    public Html5AjaxFallbackLink(final String id, final IModel<T> model)
    {
        this(id, model, null);
    }

    public Html5AjaxFallbackLink(final String id, final IModel<T> model,
            final PageParameters params)
    {
        super(id, model, params);

        add(new StatelessAjaxEventBehavior("click")
        {
            private static final long serialVersionUID = -8445395501430605953L;

            @Override
            protected void updateAjaxAttributes(AjaxRequestAttributes attributes)
            {
                super.updateAjaxAttributes(attributes);
                Html5AjaxFallbackLink.this.updateAjaxAttributes(attributes);
            }

            @Override
            protected PageParameters getPageParameters()
            {
                return Html5AjaxFallbackLink.this.getNewPageParameters();
            }

            @Override
            @SuppressWarnings("synthetic-access")
            protected void onComponentTag(final ComponentTag tag)
            {
                // only render handler if link is enabled
                if (isLinkEnabled())
                {
                    super.onComponentTag(tag);
                }
            }

            @Override
            protected void onEvent(final AjaxRequestTarget target)
            {
                onClick(target);
                target.add(Html5AjaxFallbackLink.this);
            }
        });
    }
    
	protected PageParameters getNewPageParameters() {
    	return getPageParameters();
    }
    
    /**
     * 
     * @return call decorator to use or null if none
     */
    protected void updateAjaxAttributes(AjaxRequestAttributes attributes)
    {
    }

    /**
     * @see Link#onClick()
     */
    @Override
    public final void onClick()
    {
        onClick(null);
    }

    /**
     * Callback for the onClick event. If ajax failed and this event was
     * generated via a normal link the target argument will be null
     * 
     * @param target
     *            ajax target if this linked was invoked using ajax, null
     *            otherwise
     */
    @Override
	public abstract void onClick(final AjaxRequestTarget target);
}
