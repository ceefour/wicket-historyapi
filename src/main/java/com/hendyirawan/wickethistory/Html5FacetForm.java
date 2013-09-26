package com.hendyirawan.wickethistory;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.model.AbstractWrapModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.stateless.StatelessAjaxFormComponentUpdatingBehavior;

/**
 *
 * @author ceefour
 */
public class Html5FacetForm extends StatelessForm<Facet> {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(Html5FacetForm.class);

	public Html5FacetForm(String id, IModel<Facet> model) {
		super(id, model);
		add(new Html5AjaxFallbackLink<Void>("dressesLink") {
			@Override
			protected PageParameters getNewPageParameters() {
				return Html5FacetForm.this.getModelObject().toPageParameters();
			}
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				final Facet facet = Html5FacetForm.this.getModelObject();
				facet.setCategoryId("dresses");
				log.info("Clicked Dresses link, facet is now: {}, new PageParams: {}",
						facet, facet.toPageParameters());
				onFacetChanged(target);
			}
			
			@Override
			protected void onComponentTag(ComponentTag tag) {
				super.onComponentTag(tag);
				final Facet facet = Html5FacetForm.this.getModelObject();
				if ("dresses".equals(facet.getCategoryId())) {
					tag.put("class", "active");
				}
			}
		});
		add(new Html5AjaxFallbackLink<Void>("shoesLink") {
			@Override
			protected PageParameters getNewPageParameters() {
				return Html5FacetForm.this.getModelObject().toPageParameters();
			}
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				final Facet facet = Html5FacetForm.this.getModelObject();
				facet.setCategoryId("shoes");
				log.info("Clicked Shoes link, facet is now: {}, new PageParams: {}",
						facet, facet.toPageParameters());
				onFacetChanged(target);
			}
			
			@Override
			protected void onComponentTag(ComponentTag tag) {
				super.onComponentTag(tag);
				final Facet facet = Html5FacetForm.this.getModelObject();
				if ("shoes".equals(facet.getCategoryId())) {
					tag.put("class", "active");
				}
			}
		});
		add(new Html5AjaxFallbackLink<Void>("bagsLink") {
			@Override
			protected PageParameters getNewPageParameters() {
				return Html5FacetForm.this.getModelObject().toPageParameters();
			}
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				final Facet facet = Html5FacetForm.this.getModelObject();
				facet.setCategoryId("bags");
				log.info("Clicked Bags link, facet is now: {}, new PageParams: {}",
						facet, facet.toPageParameters());
				onFacetChanged(target);
			}
			
			@Override
			protected void onComponentTag(ComponentTag tag) {
				super.onComponentTag(tag);
				final Facet facet = Html5FacetForm.this.getModelObject();
				if ("bags".equals(facet.getCategoryId())) {
					tag.put("class", "active");
				}
			}
		});
		
		final CheckBox sSize = new CheckBox("sSize", new AbstractWrapModel<Boolean>() {
			@Override
			public IModel<Facet> getWrappedModel() {
				return Html5FacetForm.this.getModel();
			}
			
			@Override
			public Boolean getObject() {
				return getWrappedModel().getObject().getSizeQNames().contains("base:s_size");
			}
			
			@Override
			public void setObject(Boolean state) {
				if (state) {
					getWrappedModel().getObject().getSizeQNames().add("base:s_size");
				} else {
					getWrappedModel().getObject().getSizeQNames().remove("base:s_size");
				}
			}
		});
		sSize.add(new StatelessAjaxFormComponentUpdatingBehavior("click") {
			@Override
			protected PageParameters getPageParameters() {
				return Html5FacetForm.this.getModelObject().toPageParameters();
			}
			
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				final Facet facet = Html5FacetForm.this.getModelObject();
				log.info("Checkbox S clicked, PageParams was: {}, facet is now: {}",
						getPageParameters(), Html5FacetForm.this.getModelObject());
				onFacetChanged(target);
			}
		});
		add(sSize);
		final CheckBox mSize = new CheckBox("mSize", new AbstractWrapModel<Boolean>() {
			@Override
			public IModel<Facet> getWrappedModel() {
				return Html5FacetForm.this.getModel();
			}
			
			@Override
			public Boolean getObject() {
				return getWrappedModel().getObject().getSizeQNames().contains("base:m_size");
			}
			
			@Override
			public void setObject(Boolean state) {
				if (state) {
					getWrappedModel().getObject().getSizeQNames().add("base:m_size");
				} else {
					getWrappedModel().getObject().getSizeQNames().remove("base:m_size");
				}
			}
		});
		mSize.add(new StatelessAjaxFormComponentUpdatingBehavior("click") {
			@Override
			protected PageParameters getPageParameters() {
				return Html5FacetForm.this.getModelObject().toPageParameters();
			}
			
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				final Facet facet = Html5FacetForm.this.getModelObject();
				log.info("Checkbox M clicked, PageParams was: {}, facet is now: {}",
						getPageParameters(), Html5FacetForm.this.getModelObject());
				onFacetChanged(target);
			}
		});
		add(mSize);
		final CheckBox lSize = new CheckBox("lSize", new AbstractWrapModel<Boolean>() {
			@Override
			public IModel<Facet> getWrappedModel() {
				return Html5FacetForm.this.getModel();
			}
			
			@Override
			public Boolean getObject() {
				return getWrappedModel().getObject().getSizeQNames().contains("base:l_size");
			}
			
			@Override
			public void setObject(Boolean state) {
				if (state) {
					getWrappedModel().getObject().getSizeQNames().add("base:l_size");
				} else {
					getWrappedModel().getObject().getSizeQNames().remove("base:l_size");
				}
			}
		});
		lSize.add(new StatelessAjaxFormComponentUpdatingBehavior("click") {
			@Override
			protected PageParameters getPageParameters() {
				return Html5FacetForm.this.getModelObject().toPageParameters();
			}
			
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				final Facet facet = Html5FacetForm.this.getModelObject();
				log.info("Checkbox L clicked, PageParams was: {}, facet is now: {}",
						getPageParameters(), Html5FacetForm.this.getModelObject());
				onFacetChanged(target);
			}
		});
		add(lSize);
	}
	
	protected void onFacetChanged(AjaxRequestTarget target) {
		
	}

}
