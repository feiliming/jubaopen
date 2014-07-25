package com.dsideal.jbp.bt.parts;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.dsideal.jbp.bt.model.Multiple;
import com.dsideal.jbp.bt.model.MultipleResult;

/**
 * 倍投关系计算器视图.
 * 
 * @author feilm220
 *
 */
public class MultiplePart {
	
	private Multiple multiple;
	
	private Binding zhqsBinding;
	private Binding trzsBinding;
	private Binding csbsBinding;
	private Binding zdbsBinding;
	private Binding dzjjBinding;
	private Binding zxsylBinding;
	private Binding qBinding;
	private Binding qsylBinding;
	private Binding hsylBinding;
	private Binding zdsyBinding;
	
	private TableViewer viewer;
	private WritableList input;
	private Button calcButton;
	private Button zxsylButton;
	private Button qButton;
	private Button zdsyButton;
	
	private Text zhqsText;
	private Text trzsText;
	private Text csbsText;
	private Text zdbsText;
	private Text dzjjText;
	private Text zxsylText;
	private Text qText;
	private Text qsylText;
	private Text hsylText;
	private Text zdsyText;
	
	@Inject
	private UISynchronize ui;

	@PostConstruct
	public void createControls(Composite parent){
		//初始化倍投关系
		multiple = initialMultiple();
		
		GridLayout gridLayout = new GridLayout(8, false);
		parent.setLayout(gridLayout);
		
		Label jbszLabel = new Label(parent, SWT.CENTER);
		jbszLabel.setText("基本设置");
		GridData gridData = new GridData();
		gridData.verticalSpan = 5;
		gridData.widthHint = 80;
		gridData.verticalAlignment = SWT.CENTER;
		gridData.horizontalAlignment = SWT.CENTER;
		jbszLabel.setLayoutData(gridData);
		
		Label zhqsLabel = new Label(parent, SWT.NONE);
		zhqsLabel.setText("追号期数: ");
		zhqsText = new Text(parent, SWT.BORDER);
		gridData = new GridData(100,SWT.DEFAULT);
		zhqsText.setLayoutData(gridData);
		Label zhqsUnit = new Label(parent, SWT.NONE);
		zhqsUnit.setText("期");
		gridData = new GridData();
		gridData.horizontalSpan = 5;
		zhqsUnit.setLayoutData(gridData);
		
		Label trzsLabel = new Label(parent, SWT.NONE);
		trzsLabel.setText("投入注数: ");
		trzsText = new Text(parent, SWT.BORDER);
		gridData = new GridData(100,SWT.DEFAULT);
		trzsText.setLayoutData(gridData);
		Label trzsUnit = new Label(parent, SWT.NONE);
		trzsUnit.setText("注");
		gridData = new GridData();
		gridData.horizontalSpan = 5;
		trzsUnit.setLayoutData(gridData);
		
		Label csbsLabel = new Label(parent, SWT.NONE);
		csbsLabel.setText("初始倍数: ");
		csbsText = new Text(parent, SWT.BORDER);
		gridData = new GridData(100,SWT.DEFAULT);
		csbsText.setLayoutData(gridData);
		Label csbsUnit = new Label(parent, SWT.NONE);
		csbsUnit.setText("倍，");
		
		Label zdbsLabel = new Label(parent, SWT.NONE);
		zdbsLabel.setText("最大倍数:");
		zdbsText = new Text(parent, SWT.BORDER);
		gridData = new GridData(100,SWT.DEFAULT);
		zdbsText.setLayoutData(gridData);
		Label zdbsUnit = new Label(parent, SWT.NONE);
		zdbsUnit.setText("倍");
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		zdbsUnit.setLayoutData(gridData);
		
		Label dzjjLabel = new Label(parent, SWT.NONE);
		dzjjLabel.setText("单注奖金: ");
		dzjjText = new Text(parent, SWT.BORDER);
		gridData = new GridData(100,SWT.DEFAULT);
		dzjjText.setLayoutData(gridData);
		Label dzjjUnit = new Label(parent, SWT.NONE);
		dzjjUnit.setText("元");
		gridData = new GridData();
		gridData.horizontalSpan = 5;
		dzjjUnit.setLayoutData(gridData);
		
		Label horizontalSeparator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
		gridData = new GridData();
		gridData.horizontalSpan = 8;
		gridData.widthHint = 700;
		horizontalSeparator.setLayoutData(gridData);
		
		Label syszLabel = new Label(parent, SWT.CENTER);
		syszLabel.setText("收益设置");
		gridData = new GridData();
		gridData.verticalSpan = 3;
		gridData.widthHint = 80;
		gridData.verticalAlignment = SWT.CENTER;
		gridData.horizontalAlignment = SWT.CENTER;
		syszLabel.setLayoutData(gridData);
		
		zxsylButton = new Button(parent, SWT.RADIO);
		zxsylButton.setText("收益率: ");
		zxsylButton.setSelection(true);
		zxsylText = new Text(parent, SWT.BORDER);
		gridData = new GridData(100,SWT.DEFAULT);
		zxsylText.setLayoutData(gridData);
		Label zxsylUnit = new Label(parent, SWT.NONE);
		zxsylUnit.setText("%");
		gridData = new GridData();
		gridData.horizontalSpan = 5;
		zxsylUnit.setLayoutData(gridData);
		
		qButton = new Button(parent, SWT.RADIO);
		qButton.setText("前");
		qText = new Text(parent, SWT.BORDER);
		gridData = new GridData(100,SWT.DEFAULT);
		qText.setLayoutData(gridData);
		Label qUnit = new Label(parent, SWT.NONE);
		qUnit.setText("期收益率: ");
		qsylText = new Text(parent, SWT.BORDER);
		gridData = new GridData(100,SWT.DEFAULT);
		qsylText.setLayoutData(gridData);
		Label qsylUnit = new Label(parent, SWT.NONE);
		qsylUnit.setText("%，之后收益率:");
		hsylText = new Text(parent, SWT.BORDER);
		gridData = new GridData(100,SWT.DEFAULT);
		hsylText.setLayoutData(gridData);
		Label hsylUnit = new Label(parent, SWT.NONE);
		hsylUnit.setText("%");
		
		zdsyButton = new Button(parent, SWT.RADIO);
		zdsyButton.setText("最低收益: ");
		zdsyText = new Text(parent, SWT.BORDER);
		gridData = new GridData(100,SWT.DEFAULT);
		zdsyText.setLayoutData(gridData);
		Label zdsyUnit = new Label(parent, SWT.NONE);
		zdsyUnit.setText("元");
		gridData = new GridData();
		gridData.horizontalSpan = 5;
		zdsyUnit.setLayoutData(gridData);
		
		createButton(parent);
		
		createTableViewer(parent);
		
		//数据绑定
		bindValues();
		
	}
	
	//初始化输入框数据,自动绑定到输入控件
	private Multiple initialMultiple(){
		Multiple initMultiple = new Multiple();
		initMultiple.setZhqs(30);
		initMultiple.setTrzs(1);
		initMultiple.setCsbs(1);
		initMultiple.setZdbs(100);
		initMultiple.setDzjj(19);
		initMultiple.setZxsyl(-50);
		initMultiple.setQ(10);
		initMultiple.setQsyl(-50);
		initMultiple.setHsyl(-50);
		initMultiple.setZdsy(10);
		return initMultiple;
	}
	
	//创建计算按钮
	private void createButton(final Composite parent){
		//第一种方法：使按钮在三列合并居中
		//Composite composite_button = new Composite(parent, SWT.NONE);
		//composite_button.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 3, 1));
		//composite_button.setLayout(new GridLayout(1, false));
		calcButton = new Button(parent, SWT.PUSH);
		calcButton.setText("计 算");
		calcButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStatus iStatus1 = (IStatus)zhqsBinding.getValidationStatus().getValue();
				IStatus iStatus2 = (IStatus)trzsBinding.getValidationStatus().getValue();
				IStatus iStatus3 = (IStatus)csbsBinding.getValidationStatus().getValue();
				IStatus iStatus4 = (IStatus)zdbsBinding.getValidationStatus().getValue();
				IStatus iStatus5 = (IStatus)dzjjBinding.getValidationStatus().getValue();
				IStatus iStatus6 = (IStatus)zxsylBinding.getValidationStatus().getValue();
				IStatus iStatus7 = (IStatus)qBinding.getValidationStatus().getValue();
				IStatus iStatus8 = (IStatus)qsylBinding.getValidationStatus().getValue();
				IStatus iStatus9 = (IStatus)hsylBinding.getValidationStatus().getValue();
				IStatus iStatus10 = (IStatus)zdsyBinding.getValidationStatus().getValue();
				boolean radioFlag = false;
				if(zxsylButton.getSelection()){
					if(iStatus6.isOK()){
						radioFlag = true;
					}
				}else if(qButton.getSelection()){
					if(iStatus7.isOK() && iStatus8.isOK() && iStatus9.isOK()){
						radioFlag = true;
					}
				}else if(zdsyButton.getSelection()){
					if(iStatus10.isOK()){
						radioFlag = true;
					}
				}
				if(iStatus1.isOK() && iStatus2.isOK() && iStatus3.isOK() && iStatus4.isOK() && iStatus5.isOK() && radioFlag){
					calcButton.setEnabled(false);
					calcButton.setText("计算中...");
					final List<MultipleResult> multipleResults = getMultipleResults();
					//eclipse 3.x使用，E4中使用UISynchronize ui
					//Display.getCurrent().syncExec(new Runnable() {
					ui.syncExec(new Runnable() {
						@Override
						public void run() {
							viewer.setInput(multipleResults);
						}
					});
					calcButton.setText("计 算");
					calcButton.setEnabled(true);
				}
			}
		});
		//第二种方法：使按钮在三列合并居中
		GridData gridData = new GridData();
		gridData.widthHint = 100; //按钮宽度
		gridData.horizontalSpan = 8;
		gridData.horizontalAlignment = GridData.CENTER;
		calcButton.setLayoutData(gridData);
	}
	
	//创建表格
	private void createTableViewer(Composite parent){
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setLinesVisible(true);
		
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 8;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);
		
		viewer.setContentProvider(new ArrayContentProvider());
		
		createColumns(parent, viewer);
		
	}
	
	//创建表格列
	private void createColumns(final Composite parent, final TableViewer viewer) {
		String[] titles = { "期数", "投入注数", "投入倍数", "当期投入", "累计投入", "当期收益", "盈利收益", "收益率" };
		int[] bounds = { 60, 70, 80, 100, 100, 100, 100, 100 };

		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				MultipleResult mr = (MultipleResult) element;
				return mr.getQs();
			}
		});

		col = createTableViewerColumn(titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				MultipleResult mr = (MultipleResult) element;
				return mr.getTrzs();
			}
		});
		
		col = createTableViewerColumn(titles[2], bounds[2], 2);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				MultipleResult mr = (MultipleResult) element;
				return mr.getBs();
			}
		});

		col = createTableViewerColumn(titles[3], bounds[3], 3);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				MultipleResult mr = (MultipleResult) element;
				return mr.getDqtr();
			}
		});

		col = createTableViewerColumn(titles[4], bounds[4], 4);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				MultipleResult mr = (MultipleResult) element;
				return mr.getLjtr();
			}

//			@Override
//			public Image getImage(Object element) {
//				if (((Person) element).isMarried()) {
//					return CHECKED;
//				} else {
//					return UNCHECKED;
//				}
//			}
		});
		
		col = createTableViewerColumn(titles[5], bounds[5], 5);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				MultipleResult mr = (MultipleResult) element;
				return mr.getDqsy();
			}
		});
		
		col = createTableViewerColumn(titles[6], bounds[6], 6);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				MultipleResult mr = (MultipleResult) element;
				return mr.getYlsy();
			}
		});
		
		col = createTableViewerColumn(titles[7], bounds[7], 7);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				MultipleResult mr = (MultipleResult) element;
				return mr.getSyl();
			}
		});

	}

	//创建列
	private TableViewerColumn createTableViewerColumn(String title, int bound,
			final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(false);
		column.setAlignment(SWT.CENTER);
		return viewerColumn;
	}
	
	//将输入控件和model绑定
	private void bindValues(){
		DataBindingContext ctx = new DataBindingContext();
		
		IObservableValue widgetValue = WidgetProperties.text(SWT.Modify)
				.observe(zhqsText);
		IObservableValue modelValue = BeanProperties.value(Multiple.class,
				"zhqs").observe(multiple);
		UpdateValueStrategy update = new UpdateValueStrategy();
	    update.setAfterConvertValidator(new NumberValiditor());
	    zhqsBinding = ctx.bindValue(widgetValue, modelValue, update, null);
	    ControlDecorationSupport.create(zhqsBinding, SWT.TOP | SWT.LEFT);
	    
	    widgetValue = WidgetProperties.text(SWT.Modify)
				.observe(trzsText);
		modelValue = BeanProperties.value(Multiple.class,
				"trzs").observe(multiple);
	    trzsBinding = ctx.bindValue(widgetValue, modelValue, update, null);
	    ControlDecorationSupport.create(trzsBinding, SWT.TOP | SWT.LEFT);
		
	    widgetValue = WidgetProperties.text(SWT.Modify)
				.observe(csbsText);
		modelValue = BeanProperties.value(Multiple.class,
				"csbs").observe(multiple);
	    csbsBinding = ctx.bindValue(widgetValue, modelValue, update, null);
	    ControlDecorationSupport.create(csbsBinding, SWT.TOP | SWT.LEFT);
	    
	    widgetValue = WidgetProperties.text(SWT.Modify)
	    		.observe(zdbsText);
	    modelValue = BeanProperties.value(Multiple.class,
	    		"zdbs").observe(multiple);
	    zdbsBinding = ctx.bindValue(widgetValue, modelValue, update, null);
	    ControlDecorationSupport.create(zdbsBinding, SWT.TOP | SWT.LEFT);
	    
	    widgetValue = WidgetProperties.text(SWT.Modify)
				.observe(dzjjText);
		modelValue = BeanProperties.value(Multiple.class,
				"dzjj").observe(multiple);
	    dzjjBinding = ctx.bindValue(widgetValue, modelValue, update, null);
	    ControlDecorationSupport.create(dzjjBinding, SWT.TOP | SWT.LEFT);
	    
	    widgetValue = WidgetProperties.text(SWT.Modify)
				.observe(zxsylText);
		modelValue = BeanProperties.value(Multiple.class,
				"zxsyl").observe(multiple);
	    zxsylBinding = ctx.bindValue(widgetValue, modelValue, update, null);
	    ControlDecorationSupport.create(zxsylBinding, SWT.TOP | SWT.LEFT);
	    
	    widgetValue = WidgetProperties.text(SWT.Modify)
				.observe(qText);
		modelValue = BeanProperties.value(Multiple.class,
				"q").observe(multiple);
	    qBinding = ctx.bindValue(widgetValue, modelValue, update, null);
	    ControlDecorationSupport.create(qBinding, SWT.TOP | SWT.LEFT);
	    
	    widgetValue = WidgetProperties.text(SWT.Modify)
				.observe(qsylText);
		modelValue = BeanProperties.value(Multiple.class,
				"qsyl").observe(multiple);
	    qsylBinding = ctx.bindValue(widgetValue, modelValue, update, null);
	    ControlDecorationSupport.create(qsylBinding, SWT.TOP | SWT.LEFT);
	    
	    widgetValue = WidgetProperties.text(SWT.Modify)
				.observe(hsylText);
		modelValue = BeanProperties.value(Multiple.class,
				"hsyl").observe(multiple);
	    hsylBinding = ctx.bindValue(widgetValue, modelValue, update, null);
	    ControlDecorationSupport.create(hsylBinding, SWT.TOP | SWT.LEFT);
	    
	    widgetValue = WidgetProperties.text(SWT.Modify)
				.observe(zdsyText);
		modelValue = BeanProperties.value(Multiple.class,
				"zdsy").observe(multiple);
		zdsyBinding = ctx.bindValue(widgetValue, modelValue, update, null);
	    ControlDecorationSupport.create(zdsyBinding, SWT.TOP | SWT.LEFT);
	}
	
	//计算表格数据
	private List<MultipleResult> getMultipleResults(){
		List<MultipleResult> mrList = new ArrayList<MultipleResult>();
		DecimalFormat df = new DecimalFormat("###.00");
		Integer zxsyl = multiple.getZxsyl();
		
		Integer qs = 0;
		Integer trzs = multiple.getTrzs();
		Integer bs = multiple.getCsbs();
		Integer dqtr = 0;
		Integer ljtr = 0;
		Integer dqsy = 0;
		Integer ylsy = 0;
		Integer zdbs = multiple.getZdbs();
		Integer q = multiple.getQ();
		Integer qsyl = multiple.getQsyl();
		Integer hsyl = multiple.getHsyl();
		
		boolean r1 = zxsylButton.getSelection();
		boolean r2 = qButton.getSelection();
		boolean r3 = zdsyButton.getSelection();
		if(r1){
			while(qs < multiple.getZhqs()){
				MultipleResult mr = new MultipleResult();
				
				if(bs > zdbs){
					break;
				}
				dqtr = 2 * bs * trzs;
				dqsy = multiple.getDzjj() * bs;
				ljtr = ljtr + dqtr;
				ylsy = dqsy - ljtr;
				double syl = (ylsy*100.0 / ljtr);
				if(syl < zxsyl){
					bs = bs + 1;
					ljtr = ljtr - dqtr;
					continue;
				}
				qs = qs + 1;
				mr.setQs(String.valueOf(qs));
				mr.setTrzs(String.valueOf(trzs));
				mr.setBs(String.valueOf(bs));
				mr.setDqtr(String.valueOf(dqtr));
				mr.setLjtr(String.valueOf(ljtr));
				mr.setDqsy(String.valueOf(dqsy));
				mr.setYlsy(String.valueOf(ylsy));
				mr.setSyl(df.format(syl) + "%");
				
				mrList.add(mr);
			}
		}else if(r2){
			while(qs < multiple.getZhqs()){
				MultipleResult mr = new MultipleResult();
				
				if(bs > zdbs){
					break;
				}
				
				dqtr = 2 * bs * trzs;
				dqsy = multiple.getDzjj() * bs;
				ljtr = ljtr + dqtr;
				ylsy = dqsy - ljtr;
				double syl = (ylsy*100.0 / ljtr);
				if(q >= qs+1){
					if(syl < qsyl){
						bs = bs + 1;
						ljtr = ljtr - dqtr;
						continue;
					}
				}else{
					if(syl < hsyl){
						bs = bs + 1;
						ljtr = ljtr - dqtr;
						continue;
					}
				}
				qs = qs + 1;
				mr.setQs(String.valueOf(qs));
				mr.setTrzs(String.valueOf(trzs));
				mr.setBs(String.valueOf(bs));
				mr.setDqtr(String.valueOf(dqtr));
				mr.setLjtr(String.valueOf(ljtr));
				mr.setDqsy(String.valueOf(dqsy));
				mr.setYlsy(String.valueOf(ylsy));
				mr.setSyl(df.format(syl) + "%");
				
				mrList.add(mr);
			}
		}else if(r3){
			while(qs < multiple.getZhqs()){
				MultipleResult mr = new MultipleResult();
				
				if(bs > zdbs){
					break;
				}
				dqtr = 2 * bs * trzs;
				dqsy = multiple.getDzjj() * bs;
				ljtr = ljtr + dqtr;
				ylsy = dqsy - ljtr;
				if(ylsy < multiple.getZdsy()){
					bs = bs + 1;
					ljtr = ljtr - dqtr;
					continue;
				}
				double syl = (ylsy*100.0 / ljtr);
				qs = qs + 1;
				mr.setQs(String.valueOf(qs));
				mr.setTrzs(String.valueOf(trzs));
				mr.setBs(String.valueOf(bs));
				mr.setDqtr(String.valueOf(dqtr));
				mr.setLjtr(String.valueOf(ljtr));
				mr.setDqsy(String.valueOf(dqsy));
				mr.setYlsy(String.valueOf(ylsy));
				mr.setSyl(df.format(syl) + "%");
				
				mrList.add(mr);
			}
		}

		return mrList;
	}
	
	@PreDestroy
	public void dispose(){
		if(!calcButton.isDisposed()){
			dispose();
		}
		if(!zxsylButton.isDisposed()){
			dispose();
		}
		if(!qButton.isDisposed()){
			dispose();
		}
		if(!zdsyButton.isDisposed()){
			dispose();
		}
		if(!qText.isDisposed()){
			dispose();
		}
	}
}
