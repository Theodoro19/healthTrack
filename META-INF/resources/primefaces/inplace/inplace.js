PrimeFaces.widget.Inplace=PrimeFaces.widget.BaseWidget.extend({init:function(a){this._super(a);this.display=$(this.jqId+"_display");this.content=$(this.jqId+"_content");this.cfg.formId=this.jq.parents("form:first").attr("id");var c=this;if(!this.cfg.disabled){if(this.cfg.toggleable){this.display.bind(this.cfg.event,function(){c.show()});this.display.mouseover(function(){$(this).toggleClass("ui-state-highlight")}).mouseout(function(){$(this).toggleClass("ui-state-highlight")})}else{this.display.css("cursor","default")}if(this.cfg.editor){this.cfg.formId=$(this.jqId).parents("form:first").attr("id");this.editor=$(this.jqId+"_editor");var b=this.editor.children(".ui-inplace-save"),d=this.editor.children(".ui-inplace-cancel");PrimeFaces.skinButton(b).skinButton(d);b.click(function(f){c.save(f)});d.click(function(f){c.cancel(f)})}}},show:function(){this.toggle(this.content,this.display)},hide:function(){this.toggle(this.display,this.content)},toggle:function(a,b){var c=this;if(this.cfg.effect==="fade"){b.fadeOut(this.cfg.effectSpeed,function(){a.fadeIn(c.cfg.effectSpeed);c.postShow()})}else{if(this.cfg.effect==="slide"){b.slideUp(this.cfg.effectSpeed,function(){a.slideDown(c.cfg.effectSpeed);c.postShow()})}else{if(this.cfg.effect==="none"){b.hide();a.show();c.postShow()}}}},postShow:function(){this.content.find("input:text,textarea").filter(":visible:enabled:first").focus().select();PrimeFaces.invokeDeferredRenders(this.id)},getDisplay:function(){return this.display},getContent:function(){return this.content},save:function(c){var a={source:this.id,update:this.id,process:this.id,formId:this.cfg.formId};if(this.hasBehavior("save")){var b=this.cfg.behaviors.save;b.call(this,a)}else{PrimeFaces.ajax.AjaxRequest(a)}},cancel:function(c){var a={source:this.id,update:this.id,process:this.id,formId:this.cfg.formId};a.params=[{name:this.id+"_cancel",value:true}];if(this.hasBehavior("cancel")){var b=this.cfg.behaviors.cancel;b.call(this,a)}else{PrimeFaces.ajax.AjaxRequest(a)}},hasBehavior:function(a){if(this.cfg.behaviors){return this.cfg.behaviors[a]!==undefined}return false}});