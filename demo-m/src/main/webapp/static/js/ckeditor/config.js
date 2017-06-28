CKEDITOR.editorConfig = function(config){
	var editorFileUrl = context_path_ + '/sys/file/upload/ckeditor';
	//config.uiColor = '#AADC6E';
	//config.resize_enabled = false;
	config.language = 'zh-cn';
	config.removePlugins = 'elementspath';
	config.toolbarCanCollapse = true;
	config.toolbarStartExpanded = true;
	
	config.filebrowserUploadUrl = editorFileUrl;
	config.filebrowserImageUploadUrl= editorFileUrl;
	
	config.toolbar_Full = 
		[ 
		    ['Source','-','NewPage','Preview','-','Templates'], 
		    ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'], 
		    ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'], ['TextColor','BGColor'], ['ShowBlocks', 'Maximize', 'toolbarCollapse'], 
		    ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'], 
		    ['BidiLtr', 'BidiRtl'], 
		    ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'], 
		    ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote','CreateDiv'], 
		    ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'], 
		    ['Link','Unlink','Anchor'], 
		    ['Image','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'], 
		    ['Styles','Format','Font','FontSize'], 
	]; 
};
